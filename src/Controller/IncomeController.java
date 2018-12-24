package Controller;

import Model.MenuToIncome;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class IncomeController {
    @FXML
    TableView incomeTable;
    @FXML
    TableColumn<MenuToIncome,String>name,price;
    @FXML
    Button show,back;
    @FXML
    Label text;

    @FXML public void gotoincome(ActionEvent event){
        back = (Button) event.getSource();
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/JavaFx/Main1.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    ObservableList<MenuToIncome> Data3= FXCollections.observableArrayList();
    public void Start(){
        Data3.clear();
            String driver = "org.sqlite.JDBC";
            String urlDB = "jdbc:sqlite:FoodShop.db";
            try{
                Class.forName(driver);
                Connection connection = DriverManager.getConnection(urlDB);
                Statement statement = connection.createStatement();
                String sql = "Select * From Invoid";
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    Data3.add(new MenuToIncome(resultSet.getString("name"),resultSet.getString("price")));

                }
                connection.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    public void initialize() {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        incomeTable.setItems(Data3);
        Start();
    }
}
