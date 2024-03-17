package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.adapter.out.db;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.Customer;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.CustomerId;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.usecase.out.FindCustomer;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.usecase.out.SaveCustomer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerRepository implements SaveCustomer, FindCustomer {
    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerToJpaEntityMapper customerToJpaEntityMapper;

    public CustomerRepository(CustomerJpaRepository customerJpaRepository, CustomerToJpaEntityMapper customerToJpaEntityMapper) {
        this.customerJpaRepository = customerJpaRepository;
        this.customerToJpaEntityMapper = customerToJpaEntityMapper;
    }

    @Override
    public Optional<Customer> findById(CustomerId customerId) {
        return customerJpaRepository.findById(customerId.value()).map(customerToJpaEntityMapper::mapToDomain);
    }

    @Override
    public Customer save(Customer customer) {
        return customerToJpaEntityMapper.mapToDomain(customerJpaRepository.save(customerToJpaEntityMapper.mapToJpaEntity(customer)));
    }
}
