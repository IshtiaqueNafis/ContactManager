package ViewAlldata;

import Classes.Address;
import Classes.Contact;
import Classes.ContactManager;
import Classes.MyDate;
import ContactEditANDADD.EditContact;
import interfaces.SetScene;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.util.*;

public class ShowAllData implements SetScene, Initializable {
/*************************** setting up table columns  **************************************/
    @FXML
    private TableView<ContactManager> contactTable; // table will only take contact manager

    @FXML
    private TableColumn<ContactManager, String> name; // name will be given in full format with first name and last name

    @FXML
    private TableColumn<ContactManager, String> email; // email is in string format

    @FXML
    private TableColumn<ContactManager, String> phone; // phone is in string format

    @FXML
    private TableColumn<ContactManager, String> address; // in full format with streetname postal code city and country

    @FXML
    private TableColumn<ContactManager, String> bday; //birthday will be on String format with DD-month-year;
    @FXML
    private TableColumn<ContactManager, String> note; // will show string format for notes




/***********************Setting up textfield for searching  *********************************/

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    // first and last name is responsible for searching user by first name and last name

    @FXML
    private TextField city;

    //// will search button for cityname


    /***********************buttons  *********************************/
    @FXML
    private Button cityok;
    // cityok responsbile for searching city name on table view


    @FXML
    private Button search;
    // this is responsible for showing new search based on first name and last name

    @FXML
    private Button mainmenu; // will go back to manin menu

    @FXML
    private Button delete;

    @FXML
    private Button add;

    @FXML
    private Button editEmail;

    @FXML
    private BorderPane mypane;


    /********************************In class methods ****************************************************/
private ObservableList<ContactManager>  getcontact(){
    ObservableList<ContactManager> contactManager = FXCollections.observableArrayList();
    List<List<String>> records = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader("ContactData.csv"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            records.add(Arrays.asList(values));
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    for (List<String> record : records) {
        Address address = new Address(record.get(4),record.get(5),record.get(6),record.get(7),record.get(8));
        /*
        // setting up addres so its east to read.
            at get 4 index street adreess 1 is located
            // index 5 street address 2 aka postal code
            //index 6-city
            // index 7-province
            index 8 - country
         */
        String[] convertedDate =  record.get(9).split("-"); // split into array to so its easy to read and pass it

        MyDate date = new MyDate(
          Integer.parseInt(convertedDate[0]), // month
          Integer.parseInt(convertedDate[1]),//day
          Integer.parseInt(convertedDate[2])); // year
        Contact contact = new Contact(record.get(0),record.get(1),record.get(2),record.get(3),address,date,record.get(10));
        /*
        in csv file index 0 includes first name name, 1 include last name 2 is phone 3 is email
        // rest are address which is part contnact class
        // then rest is date and at 12 index speical note will be saved.
         */
        contactManager.add(new ContactManager(contact));
    }

    return  contactManager;


}



    public void deleteData(javafx.event.ActionEvent event) {
      String deletedDataID  = contactTable.getItems().remove(contactTable.getSelectionModel().getSelectedIndex()).getContact().toString();
      // get the obeject for the whole table from the list
        // get it as a string so it can be compared with to string method for contact

      removeProduct(deletedDataID);

    }



    private void removeProduct(String deletedDataID){
    ArrayList<String> contactStringList = new ArrayList<>(); // this will store what ever things that are not being deleted

    for(int i =0; i<getcontact().size();i++){
        if(getcontact().get(i).getContact().toString().equals(deletedDataID)){
            // check whter delete data id equal to string method
            getcontact().remove(getcontact().get(i)); // this removes it
        }else {
            contactStringList.add(getcontact().get(i).getContact().toString()); // save each element as string
        }
    }
        writeFile(contactStringList); // write to file without appending so the data can be read again
    }
    private void writeFile(ArrayList<String> stringData) {
try (FileWriter writer = new FileWriter("ContactData.csv");
            //data will be added in the end.
            BufferedWriter bw = new BufferedWriter(writer)) {
    for (String stringDatum : stringData) {
        bw.write(stringDatum);
        bw.newLine();
    }


       } catch (IOException e) {
           e.printStackTrace();
        }
   }

//search for city
//

    private ObservableList<ContactManager> filterList(List<ContactManager> list, String searchText){ // searcb text will look for city
        List<ContactManager> filteredList = new ArrayList<>();
        for (ContactManager contactManager : list){

            if(contactManager.getContact().getHomeAddress().getCity().toLowerCase().contains(searchText.toLowerCase())){
                filteredList.add(contactManager); // look for match if a match is found add it to filtered list
            }
        }
        return FXCollections.observableList(filteredList); // returns it
    }
    public void searchResult(ActionEvent event) {

        city.textProperty().addListener((observable,oldValue,newValue)-> // city key will set property and value
               contactTable.setItems(filterList(getcontact(),newValue)));
    }






    /**************Overriding methods********************************* */

    @Override
    public void switchScene(ActionEvent event) throws IOException, ParseException {
      if (event.getSource()== mainmenu){
          setscence(event,"/sample/Main Menu.fxml"); // go back to main menu
      }
     else if(event.getSource() == delete){
          Stage stage = (Stage)mypane.getScene().getWindow();
          Alert.AlertType type = Alert.AlertType.CONFIRMATION;
          Alert alert = new Alert(type,"");
          alert.initModality(Modality.APPLICATION_MODAL); // this disables the background screen
          alert.initOwner(stage);
          alert.getDialogPane().setContentText("Delete this contact");
          alert.getDialogPane().setHeaderText("delete is permanent there is no going back");

          Optional<ButtonType> result = alert.showAndWait();
          if(result.get()==ButtonType.OK){
          deleteData(event);

          }

      }
     else if(event.getSource()== add){ // redirect to adding new data
          setscence(event,"/ContactEditANDADD/addNewContact.fxml");
      }
     else if(event.getSource() == editEmail){
        changeScence(event);
      }




    }

    @Override
    public void setscence(ActionEvent event, String scenceName) throws IOException {

        Parent tableviewParent = FXMLLoader.load(getClass().getResource(scenceName)); // page that will be targetted on new scnece
        Scene mainMenuScene = new Scene(tableviewParent); // set up new scence on cusntructor

        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        // this is based on upcasting and downcasting method
        // stage will be upcared
        //event gets converted to node
        window.setScene(mainMenuScene);
        window.show();
    }

    @Override
    public void changeScence(ActionEvent event) throws IOException, ParseException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ContactEditANDADD/editContact.fxml"));
        Parent editViewParent = loader.load();

        Scene editViewScence = new Scene(editViewParent);
        EditContact controller = loader.getController();
        controller.initData(contactTable.getSelectionModel().getSelectedIndex());


        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        // this is based on upcasting and downcasting method
        // stage will be upcared
        //event gets converted to node
        window.setScene(editViewScence);
        window.show();

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getContact().getFirstName() +" "+ cellData.getValue().getContact().getLastName()));
        email.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getContact().getEmail()));
        phone.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getContact().getPhone()));
        address.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getContact().getHomeAddress().toString()));
        bday.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getContact().getDate().toString()));
        note.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getContact().getNotes()));

         contactTable.setItems(getcontact());
    }
}
