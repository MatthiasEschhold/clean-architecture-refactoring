package de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller;

import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database.OrderDataDbo;

public class CreateOrderRequest {
    private OrderDataDbo orderData;
    private String customerId;

    public OrderDataDbo getOrderData() {
        return orderData;
    }

    public void setOrderData(OrderDataDbo orderData) {
        this.orderData = orderData;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
