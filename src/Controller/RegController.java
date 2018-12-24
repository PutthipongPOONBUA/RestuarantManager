package Controller;

import Model.database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class RegController {
    @FXML
    Button back,addNewEmploy;
    @FXML
    TextField id,pass,name;


    @FXML public void toBack(ActionEvent event){
        back = (Button) event.getSource();
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/JavaFx/Login.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void addEmploy() {
        database.addEmploy(id.getText(), pass.getText(), name.getText());
        id.clear();
        pass.clear();
        name.clear();
    }
}
