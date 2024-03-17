package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.exception;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.CustomerId;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(CustomerId customerId) {
        super("Customer with id " + customerId + " not found");
    }
}
