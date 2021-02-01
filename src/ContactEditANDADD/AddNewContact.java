package ContactEditANDADD;

import interfaces.SetScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class AddNewContact implements SetScene {
 // inpput textfield for taking input
    @FXML
    private TextField firstName;
    @FXML

    private TextField lastname;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    // adress constructors field
    @FXML
    private TextField str1;

    @FXML
    private TextField postalCode;

    @FXML
    private TextField city;

    @FXML
    private TextField province;

    @FXML
    private TextField country;


// date construscors values
    @FXML
    private DatePicker date;

// last property for contact
    @FXML
    private TextArea note;

  // buttons
    @FXML
    private Button add; // will add new data and redictrct to page at the front.

    @FXML
    private Button cancel; // cancel will go back to utility page

    @FXML
    private AnchorPane mypane;



    /***************************************Class methods***************************/
    private boolean checkForInput(){
        return !firstName.getText().isEmpty() && !lastname.getText().isEmpty()
                &&!phone.getText().isEmpty() && !email.getText().isEmpty()
                && !str1.getText().isEmpty() && !postalCode.getText().isEmpty()
                && !city.getText().isEmpty() && !province.getText().isEmpty()
                && !country.getText().isEmpty() && date.getValue() !=null &&
                !note.getText().isEmpty(); // this is checking for boolean value all fields are mandotry by default

    }
    private String returnString(){
        String formattedDate = date.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        return firstName.getText()+','+lastname.getText()+","+phone.getText()+","+email.getText()+","+str1.getText()+","+
                postalCode.getText()+","+city.getText()+","+province.getText()+","+country.getText()+","+formattedDate+","+note.getText();
    }
    private void writeFile(String output){
        try (FileWriter writer = new FileWriter("ContactData.csv",true);
             //data will be added in the end.
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(output);
            bw.newLine();
            //writting the whole data in csv

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    /* interface methods  */
    @Override
    public void switchScene(ActionEvent event) throws IOException {
        if (event.getSource()==add && checkForInput()){ // check when add button is clicked
            Stage stage = (Stage)mypane.getScene().getWindow();
            Alert.AlertType type = Alert.AlertType.CONFIRMATION;
            Alert alert = new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL); // this disables the background screen
            alert.initOwner(stage);

            alert.getDialogPane().setContentText("Are tou sure tou want to submit");
            alert.getDialogPane().setHeaderText("press ok to confirm cancel to make further changes");

            Optional<ButtonType> result = alert.showAndWait();
            if(result.get()==ButtonType.OK){
            writeFile(returnString()); // this writes to file
           setscence(event,"/ViewAlldata/ShowAllData.fxml");  // then goes to show that data has been added
            }

        }
       else if (event.getSource() == cancel){
            setscence(event,"/sample/Main Menu.fxml");
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
    public void changeScence(ActionEvent event) {

    }


}
