package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.usecase.out;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.Customer;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.CustomerId;

public interface FetchCustomer {
    Customer fetch(CustomerId customerId);
}
