package de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDataDboAccessor {
    private OrderDataCrudRepository orderCrudRepository;

    public OrderDataDboAccessor(OrderDataCrudRepository orderCrudRepository) {
        this.orderCrudRepository = orderCrudRepository;
    }
    public OrderDataDbo findOrder(int id) {
        return orderCrudRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<OrderDataDbo> findOrders() {
        return (List) orderCrudRepository.findAll();
    }

    public OrderDataDbo saveOrder(OrderDataDbo orderDataDbo) {
        return orderCrudRepository.save(orderDataDbo);
    }
}
