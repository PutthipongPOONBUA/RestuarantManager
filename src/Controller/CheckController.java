package Controller;

import Model.MenuToCheck;
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
import java.util.ArrayList;


public class CheckController {
    @FXML
    ChoiceBox<String>choiceBox;
    @FXML
    Button home,dis10,dis15,dis20,check,show;
    @FXML
    Label totalPrice;
    @FXML
    TableView listCheck;
    @FXML
    TableColumn<MenuToCheck,String>name,price,table;

    ObservableList<MenuToCheck> Data= FXCollections.observableArrayList();
    ArrayList<MenuToCheck> kawin = Start();
    double total=0;
// back to home scaen
    @FXML public void toMainPageBtn(ActionEvent event){
        home = (Button) event.getSource();
        Stage stage = (Stage) home.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/JavaFx/Main2.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load(), 1280, 720));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MenuToCheck> Start(){
        ArrayList<MenuToCheck> arrayList = new ArrayList<>();
        String driver = "org.sqlite.JDBC";
        String urlDB = "jdbc:sqlite:FoodShop.db";
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            Statement statement = connection.createStatement();
            String sql = "Select * From table1";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                arrayList.add(new MenuToCheck(resultSet.getString("name"),resultSet.getString("price"),resultSet.getString("table")));

            }
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void addfunc(){
        Data.clear();
        for(MenuToCheck x : kawin)
            Data.add(new MenuToCheck(x.getName(),x.getPrice(),x.getTable()));
        setTotal();
    }

    public void setTotal(){

        for(MenuToCheck i : kawin)
            total+= Integer.parseInt(i.getPrice());
        totalPrice.setText(String.valueOf(total));
    }

    public void initialize() {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.setCellValueFactory(new PropertyValueFactory<>("table"));

        listCheck.setItems(Data);
        addfunc();
        choiceBox.getItems().add("1");
        choiceBox.getItems().add("2");
        choiceBox.getItems().add("3");

    }
    public void setDis10(){
        total=0;
        for(MenuToCheck i : kawin)
            total+= Integer.parseInt(i.getPrice());
        totalPrice.setText(String.valueOf(total*0.9));
    }
    public void setDis15(){
        total=0;
        for(MenuToCheck i : kawin)
            total+= Integer.parseInt(i.getPrice());
        totalPrice.setText(String.valueOf(total*0.85));
    }
    public void setDis20(){
        total=0;
        for(MenuToCheck i : kawin)
            total+= Integer.parseInt(i.getPrice());
        totalPrice.setText(String.valueOf(total*0.80));
    }

    public void setCheck(){
        database.remove();
        totalPrice.setText("0");
        Data.clear();
    }
    public void setShow(){
        String tableeeee = choiceBox.getValue();
        Data.clear();
        total = 0;
        for(MenuToCheck x : kawin){
            if(x.getTable().equalsIgnoreCase(tableeeee)){
                Data.add(new MenuToCheck(x.getName(),x.getPrice(),x.getTable()));
                total += Double.parseDouble(x.getPrice());
            }
        }
        totalPrice.setText(String.valueOf(total));
    }
}




