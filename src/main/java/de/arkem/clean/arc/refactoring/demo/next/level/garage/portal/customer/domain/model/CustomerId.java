package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model;

public record CustomerId(Integer value) {
    public CustomerId {
        validate(value);
    }

    private void validate(Integer value) {
        if (value == null) {
            throw new IllegalArgumentException("Customer id must not be null");
        }
        if(value < 0) {
            throw new IllegalArgumentException("Customer id must not be negative");
        }
    }
}
