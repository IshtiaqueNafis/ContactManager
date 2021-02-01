package ContactEditANDADD;
import Classes.Address;
import Classes.Contact;
import Classes.ContactManager;
import Classes.MyDate;
import interfaces.SetScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EditContact implements SetScene {





    @FXML
    private TextField ID;
    @FXML
    private TextField country;

    @FXML
    private TextArea note;

    @FXML
    private TextField firstname;

    @FXML
    private TextField province;

    @FXML
    private TextField phone;

    @FXML
    private TextField city;

    @FXML
    private TextField str1;

    @FXML
    private TextField postalcode;

    @FXML
    private TextField str2;

    @FXML
    private TextField email;

    @FXML
    private TextField lastname;

    @FXML
    private DatePicker date;


    @FXML
    private Button update;

    @FXML
    private BorderPane mypane;

    @FXML
    private Button cancel;

    private ArrayList<ContactManager> selectedContact(){ // this will get data from textfile and update it.
        ArrayList<ContactManager> contactManager = new ArrayList<>();
        List<List<String>> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Nafis Ishtiaque\\org.openjfx\\ContactManagerUpdated\\src\\ViewAlldata\\test.csv"))) {
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
        /*9
        in csv file index 0 includes first name name, 1 include last name 2 is phone 3 is email
        // rest are address which is part contnact class
        // then rest is date and at 12 index speical note will be saved.
         */
            contactManager.add(new ContactManager(contact));
        }

        return contactManager;
    }

    public void initData(int contactID) throws ParseException {
        ID.setText(String.valueOf(contactID)); // this willbe worked as key to update the values
        firstname.setText(selectedContact().get(contactID).getContact().getFirstName());
        lastname.setText(selectedContact().get(contactID).getContact().getLastName());
        phone.setText(selectedContact().get(contactID).getContact().getPhone());
        email.setText(selectedContact().get(contactID).getContact().getEmail());
        str1.setText(selectedContact().get(contactID).getContact().getHomeAddress().getStreetinfo1());
        str2.setText(selectedContact().get(contactID).getContact().getHomeAddress().getGetStreetinfo2());
        postalcode.setText(selectedContact().get(contactID).getContact().getHomeAddress().getGetStreetinfo2());
        city.setText(selectedContact().get(contactID).getContact().getHomeAddress().getCity());
        province.setText(selectedContact().get(contactID).getContact().getHomeAddress().getProvince());
        country.setText(selectedContact().get(contactID).getContact().getHomeAddress().getCountry());
        date.setValue(LocalDate.parse(selectedContact().get(contactID).getContact().getDate().getMonthShortForm().toString()));

        note.setText(selectedContact().get(contactID).getContact().getNotes());


    }







    private void convertarraytostringObject(ArrayList<ContactManager> contactManagerArrayList){
        ArrayList<String>updateddata = new ArrayList<>();
        for (ContactManager contactManager : contactManagerArrayList) {
            updateddata.add(contactManager.getContact().toString());
        }

        writetofile(updateddata);
    }



   private void writetofile(ArrayList<String>contacts){
       try (FileWriter writer = new FileWriter("ContactData.csv");
            //data will be added in the end.
            BufferedWriter bw = new BufferedWriter(writer)) {
           for (String stringDatum : contacts) {
               bw.write(stringDatum);
               bw.newLine();
           }


       } catch (IOException e) {
           e.printStackTrace();
       }
   }


private boolean checkForinput(){ //check for boolean value
        return !firstname.getText().isEmpty()
                && !lastname.getText().isEmpty()
                &&!phone.getText().isEmpty()
                && !email.getText().isEmpty()
                && !str1.getText().isEmpty()
                && !postalcode.getText().isEmpty()
                && !city.getText().isEmpty()
                && !province.getText().isEmpty()
                && !country.getText().isEmpty()
                && !note.getText().isEmpty();
}


    @Override
    public void switchScene(ActionEvent event) throws IOException, ParseException {
        if(event.getSource()==update && checkForinput()){


            Stage stage = (Stage)mypane.getScene().getWindow();
            Alert.AlertType type = Alert.AlertType.CONFIRMATION;
            Alert alert = new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL); // this disables the background screen
            alert.initOwner(stage);

            alert.getDialogPane().setContentText("ok to submit chances cancel to make m changes");
            alert.getDialogPane().setHeaderText("do you want to make the changes");

            Optional<ButtonType> result = alert.showAndWait();
            if(result.get()== ButtonType.OK){
            ArrayList<ContactManager>contactManagerArrayList = selectedContact();
            int id = Integer.parseInt(ID.getText());
            updateArraylist(contactManagerArrayList,id);
            convertarraytostringObject(contactManagerArrayList);
            setscence(event,"/ViewAlldata/ShowAllData.fxml");
            }
            }

        else if(event.getSource() == update && !checkForinput()){
            Stage stage = (Stage)mypane.getScene().getWindow();
            Alert.AlertType type = Alert.AlertType.INFORMATION;
            Alert alert = new Alert(type);
           alert.initModality(Modality.APPLICATION_MODAL);
           alert.initOwner(stage);

           alert.setTitle("ERROR");
           alert.setHeaderText("you have error with your input");
           alert.setContentText("click on ok to fix it");
           alert.showAndWait();


        }

        else if(event.getSource() == cancel){
            setscence(event,"/ViewAlldata/ShowAllData.fxml");
        }



        }




    private void updateArraylist(ArrayList<ContactManager> contactManagers,int id){
        contactManagers.get(id).getContact().setFirstName(firstname.getText()); // updating first name
        contactManagers.get(id).getContact().setLastName(lastname.getText());
        contactManagers.get(id).getContact().setPhone(phone.getText());
        contactManagers.get(id).getContact().setEmail(email.getText());
        contactManagers.get(id).getContact().getHomeAddress().setStreetinfo1(str1.getText());
        contactManagers.get(id).getContact().getHomeAddress().setGetStreetinfo2(postalcode.getText());
        contactManagers.get(id).getContact().getHomeAddress().setCity(city.getText());
        contactManagers.get(id).getContact().getHomeAddress().setProvince(province.getText());
        contactManagers.get(id).getContact().getHomeAddress().setCountry(country.getText());
        String formattedDate = date.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        String[] datepicker = formattedDate.split("-");
        contactManagers.get(id).getContact().getDate().setMonth(Integer.parseInt(datepicker[0])); // for month
        contactManagers.get(id).getContact().getDate().setDay(Integer.parseInt(datepicker[1])); // for month
        contactManagers.get(id).getContact().getDate().setYear(Integer.parseInt(datepicker[2])); // for month
        contactManagers.get(id).getContact().setNotes(note.getText());



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

    }
}
