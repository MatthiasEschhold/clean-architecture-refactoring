package de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.service;

import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database.OrderPositionDataDbo;

import java.util.UUID;

public class OrderUtil {

    //rearc
    //eliminate and move to new vehicle domain model
    /*public static boolean validateLicensePlate(String lp) {
        return true;
    }*/

    //rearc
    //eliminate and move to new vehicle domain model
    /*public static boolean isVehicleIdValid(String id) {
        return true;
    }*/

    //rearc
    //change to a factory pattern implementation
    public static OrderPositionDataDbo createOrderPositionDataDbo(String description, double quantity) {
        OrderPositionDataDbo orderPositionDataDbo = new OrderPositionDataDbo();
        orderPositionDataDbo.setPositionId(UUID.randomUUID().toString());
        orderPositionDataDbo.setPositionDescription(description);
        orderPositionDataDbo.setQuantity(quantity);
        return orderPositionDataDbo;
    }

    //rearc
    //eliminate and move to new customer domain model
    /*public static void validatePostalCode(String postalCode) {
        if (postalCode.length() != 5) {
            throw new IllegalArgumentException("Postal code must be 5 characters long");
        }
    }*/

    //rearc
    //eliminate and move to new customer domain model
    /*public static void validateCustomerId(int customerId) {
        if (customerId < 0) {
            throw new IllegalArgumentException("orderNumber is not valids");
        }
    }*/

    //rearc
    //eliminate and move to new customer domain model
    /*public static void validateCustomerResponse(CustomerResponse response) {
        if (response == null) {
            throw new IllegalArgumentException("Customer response should not be null");
        }
        if (response.getCustomerName() == null
                || response.getCustomerName().isEmpty()
                || response.getCustomerName().isBlank()
                || response.getCustomerLastName() == null
                || response.getCustomerLastName().isEmpty()
                || response.getCustomerLastName().isBlank()) {
            throw new IllegalArgumentException("Customer name should not be empty, blank or null");
        }
    }*/
}
