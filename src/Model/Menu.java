package Model;

import javafx.beans.property.SimpleStringProperty;
public class Menu {
    private final SimpleStringProperty name;
    private final SimpleStringProperty price;
    private final SimpleStringProperty type ;

    public Menu(String name, String price , String type) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
        this.type = new SimpleStringProperty((type));
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getName() {
        return name.get();
    }

    public String getPrice() {
        return price.get();
    }

    public String getType(){ return type.get();}


}
