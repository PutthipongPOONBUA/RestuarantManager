package Model;

import javafx.beans.property.SimpleStringProperty;

public class MenuToKitchen {
    private final SimpleStringProperty name;
    private final SimpleStringProperty table;


    public MenuToKitchen(String name, String table) {
        this.name = new SimpleStringProperty(name);
        this.table = new SimpleStringProperty(table);

    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setTable(String table) {
        this.table.set(table);
    }

    public String getTable() {
        return table.get();
    }

    public String getName() {
        return name.get();
    }

}
