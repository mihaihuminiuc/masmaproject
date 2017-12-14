package util;

import java.util.ArrayList;
import java.util.List;

public class ObjectItem {

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPriceTransport() {
        return priceTransport;
    }

    public void setPriceTransport(int priceTransport) {
        this.priceTransport = priceTransport;
    }

    public int getPriceDepozit() {
        return priceDepozit;
    }

    public void setPriceDepozit(int priceDepozit) {
        this.priceDepozit = priceDepozit;
    }

    private int price;
    private int quantity;
    private String ItemName;
    private int priceTransport;
    private int priceDepozit;
}
