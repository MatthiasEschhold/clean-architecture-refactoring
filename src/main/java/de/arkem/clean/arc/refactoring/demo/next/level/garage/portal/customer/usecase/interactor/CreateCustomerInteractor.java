package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.usecase.interactor;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.Customer;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.usecase.in.CreateCustomer;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.usecase.out.SaveCustomer;
import org.springframework.stereotype.Component;

@Component
public class CreateCustomerInteractor implements CreateCustomer {
    private final SaveCustomer saveCustomer;
    public CreateCustomerInteractor(SaveCustomer saveCustomer) {
        this.saveCustomer = saveCustomer;
    }

    @Override
    public Customer create(Customer customer) {
        return saveCustomer.save(customer);
    }
}
