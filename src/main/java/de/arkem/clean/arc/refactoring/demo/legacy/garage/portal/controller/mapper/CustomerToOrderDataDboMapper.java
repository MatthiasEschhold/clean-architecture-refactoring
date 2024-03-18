package de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller.mapper;

import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller.resource.OrderResource;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.Address;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.Customer;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.CustomerId;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.Name;
import org.springframework.stereotype.Component;

@Component
public class CustomerToOrderDataDboMapper {
    public Customer mapToCustomer(int customerId, OrderResource orderResource) {
        return Customer.createInitialCustomer(
                new CustomerId(customerId),
                new Name(orderResource.getCustomerName(), orderResource.getLastName()),
                new Address(orderResource.getStreet(), orderResource.getPostalCode(), orderResource.getCity()));
    }
}
