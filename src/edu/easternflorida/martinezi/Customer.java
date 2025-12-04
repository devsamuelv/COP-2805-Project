package edu.easternflorida.martinezi;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javafx.beans.property.*;

public class Customer {
    private final IntegerProperty custKey = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty phone = new SimpleStringProperty();

    public Customer(int custKey, String name, String phone) {
        this.custKey.set(custKey);
        this.name.set(name);
        this.phone.set(phone);
    }

    public int getCustKey() { return custKey.get(); }
    public IntegerProperty custKeyProperty() { return custKey; }

    public String getName() { return name.get(); }
    public StringProperty nameProperty() { return name; }

    public String getPhone() { return phone.get(); }
    public StringProperty phoneProperty() { return phone; }
}
