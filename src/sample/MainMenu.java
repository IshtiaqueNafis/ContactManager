package sample;

import interfaces.SetScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class MainMenu  implements SetScene {

    @FXML
    private Button add;

    @FXML
    private Button all;

    @FXML
    private Button searchname;

    @FXML
    private Button delete;

    @FXML
    private Button searchcity;





    public void switchScene(ActionEvent event) throws IOException {
       if (event.getSource() ==add){
           setscence(event,"/ContactEditANDADD/addNewContact.fxml");
       }else if(event.getSource()==all){
           setscence(event,"/ViewAlldata/ShowAllData.fxml");

       }else {
           setscence(event,"/ViewAlldata/ShowAllData.fxml");
       }

        }

    public void setscence(ActionEvent event,String scenceName) throws IOException {
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

