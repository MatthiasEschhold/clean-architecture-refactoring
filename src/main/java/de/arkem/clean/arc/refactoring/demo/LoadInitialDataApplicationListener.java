package de.arkem.clean.arc.refactoring.demo;

import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller.OrderRequest;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database.OrderDataCrudRepository;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database.OrderDataDbo;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.service.OrderDataService;
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
    @Autowired
    private OrderDataCrudRepository orderDataRepository;
    @Autowired
    private OrderDataService orderDataService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("Application ready event received");
        initApplicationTestData();
    }

    private void initApplicationTestData() {
        List<OrderDataDbo> orders = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            OrderRequest orderRequest = new OrderRequest();
            orderRequest.setOrderData(createOrder(i));
            orderRequest.setCustomerId("1234" + i);
            orderDataService.createOrder(orderRequest);
        }
        System.out.println("test data created");
    }

    private OrderDataDbo createOrder(int valueExtension) {
        OrderDataDbo order = new OrderDataDbo();
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
