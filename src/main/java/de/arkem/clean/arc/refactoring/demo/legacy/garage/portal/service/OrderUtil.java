package de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.service;

import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller.resource.OrderPositionResource;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database.OrderPositionDataDbo;

import java.util.UUID;

public class OrderUtil {
    public static boolean validateLicensePlate(String lp) {
        return true;
    }

    public static boolean isVehicleIdValid(String id) {
        return true;
    }

    public static OrderPositionDataDbo createOrderPositionDataDbo(String description, double quantity) {
        OrderPositionDataDbo orderPositionDataDbo = new OrderPositionDataDbo();
        orderPositionDataDbo.setPositionId(UUID.randomUUID().toString());
        orderPositionDataDbo.setPositionDescription(description);
        orderPositionDataDbo.setQuantity(quantity);
        return orderPositionDataDbo;
    }

    public static void validatePostalCode(String postalCode) {
        if (postalCode.length() != 5) {
            throw new IllegalArgumentException("Postal code must be 5 characters long");
        }
    }

    public static void validateCustomerId(String customerId) {
        if (customerId.contains("*")) {
            throw new IllegalArgumentException("orderNumber is not valids");
        }
    }

    /**
     * @refactoring: technical debt due to unclear responsibilities
     * historically only the first needed customer properties were validated,
     * the others are forgotten
     */
    public static void validateCustomerResponse(CustomerResponse response) {
        if (response == null) {
            throw new IllegalArgumentException("Customer response should not be null");
        }
        if (response.getCustomerName() == null
                || response.getCustomerName().isEmpty()
                || response.getCustomerName().isBlank()
                || response.getCustomerLastName() == null
                || response.getCustomerLastName().isEmpty()
                || response.getCustomerLastName().isBlank()) {
            throw new IllegalArgumentException("Customer name should not be emptym, blank or null");
        }
    }
}
