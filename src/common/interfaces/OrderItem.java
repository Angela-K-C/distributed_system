package src.common.interfaces;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private int drinkId;
    private int quantity;
    private double totalPrice;

    public OrderItem(int drinkId, int quantity, double totalPrice) {
        this.drinkId = drinkId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getDrinkId() { return drinkId; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return totalPrice; }
}
