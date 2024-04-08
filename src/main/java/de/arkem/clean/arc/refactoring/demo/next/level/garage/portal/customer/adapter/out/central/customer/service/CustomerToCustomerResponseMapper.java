package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.adapter.out.central.customer.service;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.Address;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.Customer;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.CustomerId;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.Name;
import org.springframework.stereotype.Component;

@Component
public class CustomerToCustomerResponseMapper {
    public Customer mapToCustomer(CustomerResponse customerResponse) {
        return Customer.createIntialCustomer(
                new CustomerId(customerResponse.getId()),
                new Name(customerResponse.getCustomerLastName(), customerResponse.getCustomerName()),
                new Address(customerResponse.getStreet(), customerResponse.getPostalCode(), customerResponse.getCity()));
    }

}
