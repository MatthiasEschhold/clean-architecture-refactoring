package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.usecase.interactor;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.exception.CustomerNotFoundException;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.Customer;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.CustomerId;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.usecase.in.GetCustomer;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.usecase.out.FindCustomer;
import org.springframework.stereotype.Component;

@Component
public class GetCustomerInteractor implements GetCustomer {

    private final FindCustomer findCustomer;

    public GetCustomerInteractor(FindCustomer findCustomer) {
        this.findCustomer = findCustomer;
    }

    @Override
    public Customer get(CustomerId customerId) {
        return findCustomer.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
    }
}
