package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.usecase.in;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.Customer;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.CustomerId;

public interface GetCustomer {
    Customer get(CustomerId customerId);
}
