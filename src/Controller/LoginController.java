package Controller;

import Model.User;
import Model.database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    TextField id,password;
    @FXML
    Button loginBtu,reg;
    @FXML
    javafx.scene.control.Label text;

    public void gotoReg(ActionEvent event) {
        reg = (javafx.scene.control.Button) event.getSource();
        Stage stage = (Stage) reg.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/JavaFx/Reg.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void toMainPageBtn(ActionEvent action){
        if(id.getText().equals("") || password.getText().equals("")){
            text.setText("Please input username and Password.");
            text.setVisible(true);
        }
        else if(database.login(id.getText(),password.getText())){
            User user = new User();
            String []s = database.userinfo(id.getText()).split(":");
            user.setId(s[0]);
            user.setPassword(s[1]);
            loginBtu = (Button) action.getSource();
            Stage stage = (Stage) loginBtu.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/JavaFx/Main1.fxml"));
            try {
                stage.setScene(new Scene(fxmlLoader.load(), 1280 , 720));
                stage.show();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            text.setText("username or password is wrong.");
            text.setVisible(true);
        }
    }
}


