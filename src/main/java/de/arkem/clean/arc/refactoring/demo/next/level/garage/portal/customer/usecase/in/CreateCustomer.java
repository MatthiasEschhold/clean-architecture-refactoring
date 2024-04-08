package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.usecase.in;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.Customer;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.usecase.interactor.CreateCustomerCommand;

public interface CreateCustomer {
    Customer create(CreateCustomerCommand command);

    Customer create(Customer customer);
}
