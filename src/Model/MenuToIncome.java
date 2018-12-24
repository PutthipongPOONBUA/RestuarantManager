package Model;

import javafx.beans.property.SimpleStringProperty;

public class MenuToIncome {
    private final SimpleStringProperty name;
    private final SimpleStringProperty price;


    public MenuToIncome(String name, String price) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);


    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getName() {
        return name.get();
    }

    public String getPrice() {
        return price.get();
    }

}

