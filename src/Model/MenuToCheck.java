package Model;

import javafx.beans.property.SimpleStringProperty;

public class MenuToCheck {
    private final SimpleStringProperty name;
    private final SimpleStringProperty price;
    private final SimpleStringProperty table;


    public MenuToCheck(String name, String price, String table) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
        this.table = new SimpleStringProperty(table);

    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public void setTable(String table) {
        this.table.set(table);
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
