package sample; //package name
import interfaces.SetScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML; // fxml file
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button; // button being imported
import javafx.stage.Stage;

import java.io.IOException;


public class LogInController implements SetScene { //set scence interface is responsible for setting up scence

    @FXML
    private Button log; // this is the log in button for the program.


   //interface beung overwritten
    public void switchScene(javafx.event.ActionEvent event) throws IOException {
        //clicking this button will go to main menu with all the command for the application.
        setscence(event,"Main Menu.fxml");


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