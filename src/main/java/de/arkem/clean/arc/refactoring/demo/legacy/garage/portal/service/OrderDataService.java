package de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.service;

import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database.OrderDataDbo;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database.OrderDataDboAccessor;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database.OrderPositionDataDbo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDataService {
    private OrderDataDboAccessor orderDataDboAccessor;

    //rearc
    //eliminate the direct dependency to customer
    //private CustomerService customerService = new CustomerService();

    public OrderDataService(OrderDataDboAccessor orderDataDboAccessor) {
        this.orderDataDboAccessor = orderDataDboAccessor;
    }

    public OrderDataDbo readOrder(int orderNumber) {
        OrderDataDbo order = orderDataDboAccessor.findOrder(orderNumber);
        if (order == null) {
            throw new RuntimeException("Order not found");
        }
        return order;
    }

    public List<OrderDataDbo> getAllOrders() {
        return orderDataDboAccessor.findOrders();
    }

    public OrderDataDbo createOrder(OrderDataDbo orderDataDbo, int customerId) {
        //rearc
        //eliminate the direct dependency to customer
        //CustomerResponse response = getCustomer(customerId);
        //OrderUtil.validatePostalCode(response.getPostalCode());

        //rearc
        //build back vehicle
        orderDataDbo.setVehicleId(orderDataDbo.getVehicleId());

        //rearc
        //build back customer
        orderDataDbo.setCustomerId(customerId);

        orderDataDbo.setStartDate(LocalDate.now().plusDays(1));
        orderDataDbo.setEndDate(LocalDate.now().plusDays(3));
        orderDataDbo.setCreationDate(LocalDate.now());
        orderDataDbo.setOrderNumber(LocalDate.now() + "-" + customerId);
        handleOrderPositions(orderDataDbo);
        return orderDataDboAccessor.saveOrder(orderDataDbo);
    }

    private void handleOrderPositions(OrderDataDbo orderDataDbo) {
        if (orderDataDbo.getOrderPositionDataDboList() != null && !orderDataDbo.getOrderPositionDataDboList().isEmpty()) {
            orderDataDbo.setOrderPositionDataDboList(orderDataDbo.getOrderPositionDataDboList().stream()
                    .map(op -> OrderUtil.createOrderPositionDataDbo(op.getPositionDescription(), op.getQuantity()))
                    .collect(Collectors.toList()));
        }
    }

    public OrderDataDbo updateOrder(int orderNumber, int customerId, OrderPositionDataDbo... orderPositions) {
        //rearc
        //eliminate the direct dependency to customer
        //CustomerResponse customerResponse = customerService.getCustomer(customerId);
        OrderDataDbo order = readOrder(orderNumber);
        if (order.getCustomerId() != customerId) {
            //order.setCustomerName(customerResponse.getCustomerName());
            //order.setLastName(customerResponse.getCustomerLastName());
            //order.setStreet(customerResponse.getStreet());
            //OrderUtil.validatePostalCode(customerResponse.getPostalCode());
            //order.setPostalCode(customerResponse.getPostalCode());
            throw new RuntimeException("Order update not possible, customer IDs are not matching");
        }
        if (orderPositions != null) {
            order.setOrderPositionDataDboList(List.of(orderPositions)
                    .stream()
                    .map(op -> OrderUtil.createOrderPositionDataDbo(op.getPositionDescription(), op.getQuantity()))
                    .collect(Collectors.toList()));
        }
        saveOrder(order);
        return order;
    }

    private void saveOrder(OrderDataDbo order) {
        try {
            orderDataDboAccessor.saveOrder(order);
        } catch (Exception e) {
            throw new RuntimeException("Order update not possible, database error");
        }
    }
}
