package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.usecase.interactor;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.Customer;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.CustomerId;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.PaymentMethod;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.usecase.in.CreateCustomer;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.usecase.out.FetchCustomer;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.usecase.out.SaveCustomer;
import org.springframework.stereotype.Component;

@Component
public class CreateCustomerInteractor implements CreateCustomer {
    private final SaveCustomer saveCustomer;
    private final FetchCustomer fetchCustomer;

    public CreateCustomerInteractor(SaveCustomer saveCustomer, FetchCustomer fetchCustomer) {
        this.saveCustomer = saveCustomer;
        this.fetchCustomer = fetchCustomer;
    }

    @Override
    public Customer create(CreateCustomerCommand command) {
        Customer customer = fetchCustomer.fetch(new CustomerId(command.customerId()));
        if (customer != null) {
            customer.addPaymentMethod(new PaymentMethod(command.paymentMethod(), command.paymentIdentifier()));
            return saveCustomer.save(customer);
        } else {
            throw new IllegalArgumentException("Customer not found!");
        }
    }

    @Override
    public Customer create(Customer customer) {
        return saveCustomer.save(customer);
    }
}
