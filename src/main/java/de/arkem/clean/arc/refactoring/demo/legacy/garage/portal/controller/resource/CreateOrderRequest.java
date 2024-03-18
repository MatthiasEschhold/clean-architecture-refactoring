package de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller.resource;

import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller.resource.OrderResource;

public class CreateOrderRequest {
    private OrderResource orderData;
    private int customerId;

    public OrderResource getOrderData() {
        return orderData;
    }

    public void setOrderData(OrderResource orderData) {
        this.orderData = orderData;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
