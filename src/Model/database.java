package Model;

import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Arrays;

public class database {
    private static String driver = "org.sqlite.JDBC";
    private static String urlDB = "jdbc:sqlite:FoodShop.db";

    public static boolean seach(String id) {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            Statement statement = connection.createStatement();
            String sql = "Select * From menu";
            ResultSet resultSet = statement.executeQuery(sql);
            connection.close();
            while (resultSet.next()) {
                return true;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String seachDispaly(String id) {
        String s = "";
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            Statement statement = connection.createStatement();
            String sql = "Select * From menu";
            ResultSet resultSet = statement.executeQuery(sql);
            s = resultSet.getString("CourseName") + "/" + resultSet.getString("PreCondition");
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static void addtoTable(String name){
        String [] a = name.split(":");
        System.out.println(Arrays.toString(a));

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO list VALUES('"+a[0]+"','"+a[1]+"','"+a[2]+"')";
            statement.executeUpdate(sql);
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String find(String name) {
        String s ="";
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            Statement statement = connection.createStatement();
            String sql = "Select * From menu";
            ResultSet resultSet = statement.executeQuery(sql);
            s= resultSet.getString("name")+":"+resultSet.getString("price");
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }
    public static void remove(){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Delete From table1";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public  static void addCourse(String name,String price,String type){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Insert Into menu Values('"+name+"','"+price+"','"+type+"')";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public  static void addTolist(String name, String price,String table){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Insert Into table1 Values('"+name+"','"+price+"','"+table+"')";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public  static void addEmploy(String id,String password,String name){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Insert Into employes Values('"+id+"','"+password+"','"+name+"')";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static boolean login(String id,String password){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select * From employes Where id = '" + id +"' and password = '"+ password+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            connection.close();
            if (resultSet.next()) {
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String userinfo(String id){
        String s = "";
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select * From employes Where id = '"+ id + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                s +=resultSet.getString("id") +":"+ resultSet.getString("password");
            }
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return s;
    }
    public  static void addTokitchen(String name, String table){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Insert Into kitchen Values('"+name+"','"+table+"')";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void removeKit(String table){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Delete From kitchen Where tablee = '"+table+"'";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public  static void addToincome(String name, String price){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Insert Into Invoid Values('"+name+"','"+price+"')";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



}
