package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.adapter.out.db;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.adapter.out.db.model.AdressJpaEntity;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.adapter.out.db.model.CustomerJpaEntity;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.adapter.out.db.model.PaymentMethodJpaEntity;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.*;
import org.springframework.stereotype.Component;

@Component
class CustomerToJpaEntityMapper {

    public CustomerJpaEntity mapToJpaEntity(Customer customer) {
        CustomerJpaEntity customerJpaEntity = new CustomerJpaEntity();
        customerJpaEntity.setId(customer.getCustomerId().value());
        customerJpaEntity.setFirstname(customer.getName().firstname());
        customerJpaEntity.setLastname(customer.getName().lastname());
        customerJpaEntity.setAdresses(customer.getAddressList().stream().map(address -> {
            AdressJpaEntity adressJpaEntity = new AdressJpaEntity();
            adressJpaEntity.setStreet(address.street());
            adressJpaEntity.setCity(address.city());
            adressJpaEntity.setPostalCode(address.postalCode());
            return adressJpaEntity;
        }).toList());
        customerJpaEntity.setPaymentMethods(customer.getPaymentMethods().stream().map(paymentMethod -> {
            PaymentMethodJpaEntity paymentMethodJpaEntity = new PaymentMethodJpaEntity();
            paymentMethodJpaEntity.setPaymentMethod(paymentMethod.paymentMethodEnum().toString());
            paymentMethodJpaEntity.setIdentifier(paymentMethod.identifier());
            return paymentMethodJpaEntity;
        }).toList());
        return customerJpaEntity;
    }

    public Customer mapToDomain(CustomerJpaEntity customerJpaEntity) {
        return new Customer(new CustomerId(customerJpaEntity.getId()),
                new Name(customerJpaEntity.getFirstname(), customerJpaEntity.getLastname()),
                customerJpaEntity.getAdresses()
                        .stream()
                        .map(adressJpaEntity ->
                                new Address(adressJpaEntity.getStreet(), adressJpaEntity.getCity(), adressJpaEntity.getPostalCode()))
                        .toList(),
                customerJpaEntity.getPaymentMethods()
                        .stream()
                        .map(paymentMethodJpaEntity ->
                                new PaymentMethod(PaymentMethodEnum.valueOf(paymentMethodJpaEntity.getPaymentMethod()),
                        paymentMethodJpaEntity.getIdentifier()))
                        .toList());
    }
}
