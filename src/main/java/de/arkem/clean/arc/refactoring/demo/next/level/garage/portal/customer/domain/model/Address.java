package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model;

public record Address(String street, String postalCode, String city) {
    public Address {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("street must not be null or empty");
        }
        if (postalCode == null || postalCode.isBlank()) {
            throw new IllegalArgumentException("postal code must not be null or empty");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("city must not be null or empty");
        }
    }
}
