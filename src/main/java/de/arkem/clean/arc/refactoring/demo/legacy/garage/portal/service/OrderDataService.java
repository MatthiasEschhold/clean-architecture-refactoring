package de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.service;

import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller.CreateOrderRequest;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database.OrderDataDbo;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database.OrderDataDboAccessor;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database.OrderPositionDataDbo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class OrderDataService {
    private OrderDataDboAccessor orderDataDboAccessor;
    private CustomerService customerService = new CustomerService();
    public OrderDataService(OrderDataDboAccessor orderDataDboAccessor) {
        this.orderDataDboAccessor = orderDataDboAccessor;
    }
    public OrderDataDbo createOrder(CreateOrderRequest createOrderRequest) {
        CustomerResponse response = getCustomer(createOrderRequest.getCustomerId());
        OrderDataDbo orderDataDbo = createOrderRequest.getOrderData();
        if(orderDataDbo.getLicensePlate() == null && orderDataDbo.getVehicleId() == null) {
            throw new RuntimeException("Order creation not possible, license plate or vehicle id required");
        }
        if(orderDataDbo.getLicensePlate() != null && !OrderUtil.validateLicensePlate(orderDataDbo.getLicensePlate())) {
            throw new RuntimeException("Order creation not possible, license plate not valid");
        }
        if(orderDataDbo.getVehicleId() != null && !OrderUtil.isVehicleIdValid(orderDataDbo.getLicensePlate())) {
            throw new RuntimeException("Order creation not possible, vehicle id not valid");
        }
        OrderUtil.validatePostalCode(response.getPostalCode());
        orderDataDbo.setCustomerName(response.getCustomerName());
        orderDataDbo.setLastName(response.getCustomerLastName());
        orderDataDbo.setStreet(response.getStreet());
        orderDataDbo.setPostalCode(response.getPostalCode());
        orderDataDbo.setStartDate(LocalDate.now().plusDays(1));
        orderDataDbo.setEndDate(LocalDate.now().plusDays(3));
        orderDataDbo.setCreationDate(LocalDate.now());
        orderDataDbo.setOrderNumber(LocalDate.now() + "-" + createOrderRequest.getCustomerId());
        handleOrderPositions(orderDataDbo);
        return orderDataDboAccessor.saveOrder(orderDataDbo);
    }

    private void handleOrderPositions(OrderDataDbo orderDataDbo) {
        if(orderDataDbo.getOrderPositionDataDboList() != null && !orderDataDbo.getOrderPositionDataDboList().isEmpty()) {
            orderDataDbo.setOrderPositionDataDboList(orderDataDbo.getOrderPositionDataDboList().stream()
                    .map(op -> OrderUtil.createOrderPositionDataDbo(op.getPositionDescription(), op.getQuantity()))
                    .collect(Collectors.toList()));
        }
    }

    private CustomerResponse getCustomer(String customerId) {
        if(customerId == null) {
            throw new RuntimeException("Order creation not possible, customer id required");
        }
        CustomerResponse customerResponse = customerService.getCustomer(customerId);
        if(customerResponse == null) {
            throw new RuntimeException("Order creation not possible, customer not found");
        }
        return customerResponse;
    }

    public OrderDataDbo updateOrder(int orderNumber, String customerId, OrderPositionDataDbo... orderPositions) {
        OrderDataDbo order = null;
        CustomerResponse customerResponse = customerService.getCustomer(customerId);
        try {
            order = readOrder(orderNumber);
            if(customerResponse != null) {
                order.setCustomerName(customerResponse.getCustomerName());
                order.setLastName(customerResponse.getCustomerLastName());
                order.setStreet(customerResponse.getStreet());
                OrderUtil.validatePostalCode(customerResponse.getPostalCode());
                order.setPostalCode(customerResponse.getPostalCode());
            }
            if(orderPositions != null) {
                order.setOrderPositionDataDboList(List.of(orderPositions).stream().map(op -> OrderUtil.createOrderPositionDataDbo(op.getPositionDescription(), op.getQuantity()))
                        .collect(Collectors.toList()));
            }
        } catch (Exception e) {
            throw new RuntimeException("Order update not possible, order not found");
        }
        try {
            orderDataDboAccessor.saveOrder(order);
        } catch (Exception e) {
            throw new RuntimeException("Order update not possible, database error");
        }
        return order;
    }

    public OrderDataDbo readOrder(int orderNumber) {
        OrderDataDbo order = orderDataDboAccessor.findOrder(orderNumber);
        if(order == null) {
            throw new RuntimeException("Order not found");
        }
        return order;
    }

    public List<OrderDataDbo> getAllOrders() {
        return orderDataDboAccessor.findOrders();
    }
}
