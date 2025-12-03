package edu.easternflorida.joneso;

public class Order {

    private int orderKey;
    private int custKey;
    private String status;
    private double totalPrice;
    private String orderDate;

    public Order(int orderKey, int custKey, String status, double totalPrice, String orderDate) {
        this.orderKey = orderKey;
        this.custKey = custKey;
        this.status = status;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    public int getOrderKey() { return orderKey; }
    public int getCustKey() { return custKey; }
    public String getStatus() { return status; }
    public double getTotalPrice() { return totalPrice; }
    public String getOrderDate() { return orderDate; }
}
