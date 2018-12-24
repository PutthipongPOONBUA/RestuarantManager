package Controller;

import Model.MenuToKitchen;
import Model.database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class KitchenController {
    @FXML
    ChoiceBox<String> choiceBox;
    @FXML
    TableView<MenuToKitchen> kitTable;
    @FXML
    TableColumn<MenuToKitchen,String> name,table;
    @FXML
    Button delete,show,sentBill;

    ObservableList<MenuToKitchen> Data3= FXCollections.observableArrayList();
    public void Start(){
        Data3.clear();
        if(choiceBox.getValue().equals("All")){
            String driver = "org.sqlite.JDBC";
            String urlDB = "jdbc:sqlite:FoodShop.db";
            try{
                Class.forName(driver);
                Connection connection = DriverManager.getConnection(urlDB);
                Statement statement = connection.createStatement();
                String sql = "Select * From kitchen";
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    Data3.add(new MenuToKitchen(resultSet.getString("name"),resultSet.getString("tablee")));

                }
                connection.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            String driver = "org.sqlite.JDBC";
            String urlDB = "jdbc:sqlite:FoodShop.db";
            try{
                Class.forName(driver);
                Connection connection = DriverManager.getConnection(urlDB);
                Statement statement = connection.createStatement();
                String sql = "Select * From kitchen Where tablee = '"+choiceBox.getValue() + "'";
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    Data3.add(new MenuToKitchen(resultSet.getString("name"),resultSet.getString("tablee")));

                }
                connection.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void initialize(){
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.setCellValueFactory(new PropertyValueFactory<>("table"));
        choiceBox.getItems().add("1");
        choiceBox.getItems().add("2");
        choiceBox.getItems().add("3");
        choiceBox.getItems().add("All");

        choiceBox.setValue("All");
        kitTable.setItems(Data3);
        Start();

    }
    @FXML
    public void ShowBth(){
        Start();
    }
    public void drop(){
        String table = kitTable.getSelectionModel().getSelectedItem().getTable();
        database.removeKit(table);
        Start();
    }
}
