package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.usecase.out;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.Customer;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.CustomerId;

import java.util.Optional;

public interface FindCustomer {
    Optional<Customer> findById(CustomerId customerId);
}
