package Controller;

import Model.Menu;
import Model.MenuToIncome;
import Model.database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class OrderController {
    @FXML
    Button back, home, addToList, delete,sendOrder,show;
    @FXML
    ChoiceBox <String> menuChoiceBox,listTable;
    @FXML
    TableView <Menu> menu,list;
    @FXML
    TableColumn <Menu,String>nameMenu,nameList,priceMenu,priceList,typeMenu,table;


// back to Table system
    @FXML public void toBack(ActionEvent event){
        back = (Button) event.getSource();
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/JavaFx/Main2.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // back to home scaen
    @FXML public void gotoHome(ActionEvent event){
        home = (Button) event.getSource();
        Stage stage = (Stage) home.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/JavaFx/Main1.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ObservableList<Menu> Data= FXCollections.observableArrayList();
    ObservableList<Menu> Data2 = FXCollections.observableArrayList();
    ObservableList<MenuToIncome>Data4 = FXCollections.observableArrayList();
//
    public void Start(){
        Data.clear();
        if(menuChoiceBox.getValue().equals("All")){
            String driver = "org.sqlite.JDBC";
            String urlDB = "jdbc:sqlite:FoodShop.db";
            try{
                Class.forName(driver);
                Connection connection = DriverManager.getConnection(urlDB);
                Statement statement = connection.createStatement();
                String sql = "Select * From menu";
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    Data.add(new Menu(resultSet.getString("name"),resultSet.getString("price"),resultSet.getString("type")));

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
                String sql = "Select * From menu Where type = '"+menuChoiceBox.getValue() + "'";
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    Data.add(new Menu(resultSet.getString("name"),resultSet.getString("price"),resultSet.getString("type")));

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
        nameMenu.setCellValueFactory(new PropertyValueFactory<Menu,String>("name"));
        priceMenu.setCellValueFactory(new PropertyValueFactory<Menu,String>("price"));
        typeMenu.setCellValueFactory(new PropertyValueFactory<Menu,String>("type"));
        menuChoiceBox.getItems().add("All");
        menuChoiceBox.getItems().add("ทอด");
        menuChoiceBox.getItems().add("ต้ม");
        menuChoiceBox.getItems().add("ผัด");
        menuChoiceBox.getItems().add("ยำ");

        menuChoiceBox.setValue("All");
        menu.setItems(Data);
        Start();

        nameList.setCellValueFactory(new PropertyValueFactory<Menu,String>("name"));
        priceList.setCellValueFactory(new PropertyValueFactory<Menu,String>("price"));
        list.setItems(Data2);

        listTable.getItems().add("1");
        listTable.getItems().add("2");
        listTable.getItems().add("3");
    }


    // show order
    @FXML
    public void ShowBth(){
        Start();
    }
    // sent order to list table
    public void addBtn(){
        if(!menu.getSelectionModel().isEmpty())
            Data2.add(menu.getSelectionModel().getSelectedItem());

    }
    // delete order from list table
    public void setDelete(){
        if(!list.getSelectionModel().isEmpty()){
            Data2.remove(list.getSelectionModel().getSelectedItem());
        }

    }
    public void sentBtn(){
        for(Menu x : Data2){
            database.addTolist(x.getName(),x.getPrice(),listTable.getValue());
            database.addTokitchen(x.getName(),listTable.getValue());
        }
        for(MenuToIncome x : Data4){
            database.addToincome(x.getName(),x.getPrice());
        }
    }


}
