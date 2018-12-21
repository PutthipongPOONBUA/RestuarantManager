package Controller;

import javafx.beans.property.SimpleStringProperty;

public class Schedule2 {
    private final SimpleStringProperty name;
    private final SimpleStringProperty price;
    private final SimpleStringProperty table;


    public Schedule2(String name, String price,String table) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
        this.table = new SimpleStringProperty(table);

    }
    public String getTable(){
        return table.get();
    }

    public String getName() {
        return name.get();
    }

    public String getPrice() {
        return price.get();
    }

}
