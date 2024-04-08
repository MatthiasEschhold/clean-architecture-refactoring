package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model;


public record Address(String street, String postalCode, String city) {
    public Address {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("street must not be null or empty");
        }
        //rearc
        //extend validation of validation rules of OrderUtil
        validatePostalCode(postalCode);
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("city must not be null or empty");
        }
    }

    //rearc
    //moved from order util - encapsulation of domain-related validation
    private void validatePostalCode(String postalCode) {
        if (postalCode.length() != 5) {
            throw new IllegalArgumentException("Postal code must be 5 characters long");
        }
    }
}
