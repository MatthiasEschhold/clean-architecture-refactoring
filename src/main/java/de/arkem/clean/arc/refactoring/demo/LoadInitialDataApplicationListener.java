package de.arkem.clean.arc.refactoring.demo;

import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller.CreateOrderRequest;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller.OrderResourceToDboMapper;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller.resource.OrderResource;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database.OrderDataCrudRepository;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.service.OrderDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(0)
public class LoadInitialDataApplicationListener implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger logger = LoggerFactory.getLogger(LoadInitialDataApplicationListener.class);
    @Autowired
    private OrderDataCrudRepository orderDataRepository;
    @Autowired
    private OrderDataService orderDataService;
    @Autowired
    private OrderResourceToDboMapper orderResourceToDboMapper;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("Application ready event received");
        initApplicationTestData();
    }

    private void initApplicationTestData() {
        List<OrderResource> orders = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CreateOrderRequest createOrderRequest = new CreateOrderRequest();
            createOrderRequest.setOrderData(createOrder(i));
            createOrderRequest.setCustomerId("1234" + i);
            orderDataService.createOrder(orderResourceToDboMapper.mapResourceToDbo(createOrderRequest.getOrderData()), createOrderRequest.getCustomerId());
        }
        logger.info("Initial order data created");
    }

    private OrderResource createOrder(int valueExtension) {
        OrderResource order = new OrderResource();
        order.setOrderNumber(LocalDate.now() + "-" + valueExtension);
        order.setMileage(123 + valueExtension);
        order.setCreationDate(LocalDate.now());
        order.setLicensePlate("ES-EL 123" + valueExtension);
        if (valueExtension % 2 == 0) {
            order.setCustomerName("Max");
            order.setLastName("Mustermann");
            order.setEditorId("MES");
            order.setStreet("Marktstraße " + valueExtension);
        } else {
            order.setCustomerName("Ute");
            order.setLastName("Musterfrau");
            order.setEditorId("KAT");
            order.setStreet("Eckgraben Straße " + valueExtension);
        }
        order.setStartDate(LocalDate.now().plusDays(1 + valueExtension));
        order.setEndDate(LocalDate.now().plusDays(2 + valueExtension));
        order.setPostalCode("7262" + valueExtension);
        order.setVehicleId("WP0ZZZ99ZTS39215" + valueExtension);
        return order;
    }
}
