package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model;

import java.util.ArrayList;
import java.util.List;
public class Customer {
    private CustomerId customerId;
    private Name name;
    private List<Address> addressList;
    private List<PaymentMethod> paymentMethods;
    private Customer() { }
    public Customer(CustomerId customerId, Name name, List<Address> addressList, List<PaymentMethod> paymentMethods) {
        this.customerId = customerId;
        this.name = name;
        this.addressList = addressList;
        this.paymentMethods = paymentMethods;
    }
    public CustomerId getCustomerId() {
        return customerId;
    }

    public Name getName() {
        return name;
    }
    public List<Address> getAddressList() {
        return addressList;
    }
    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }
    public void addPaymentMethod(PaymentMethod paymentMethod) {
        paymentMethods.add(paymentMethod);
    }
    public void addAdress(Address address) {
        this.addressList.add(address);
    }
    public void removeAddress(Address address) {
        this.addressList.remove(address);
    }
    public static Customer createInitialCustomer(CustomerId customerId, Name name, Address address) {
        Customer customer = new Customer();
        customer.customerId = customerId;
        customer.name = name;
        customer.addressList = new ArrayList<>();
        customer.addressList.add(address);
        return customer;
    }
}
