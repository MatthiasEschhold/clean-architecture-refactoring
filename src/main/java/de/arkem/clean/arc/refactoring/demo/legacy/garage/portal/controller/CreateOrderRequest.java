package de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller;

import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller.resource.OrderResource;

public class CreateOrderRequest {
    private OrderResource orderData;
    private String customerId;

    public OrderResource getOrderData() {
        return orderData;
    }

    public void setOrderData(OrderResource orderData) {
        this.orderData = orderData;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
