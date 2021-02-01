package interfaces;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.text.ParseException;

public interface SetScene {
    public void switchScene(javafx.event.ActionEvent event) throws IOException, ParseException;
    void setscence(ActionEvent event, String scenceName) throws IOException;
    void changeScence(ActionEvent event) throws IOException, ParseException;

}
