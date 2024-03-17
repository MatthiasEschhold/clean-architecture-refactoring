package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model;

public record CustomerId(Integer value) {
    public CustomerId {
        if (value == null) {
            throw new IllegalArgumentException("customer id must not be null");
        }
    }
}
