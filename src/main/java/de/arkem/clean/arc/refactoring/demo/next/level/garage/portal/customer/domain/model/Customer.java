package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model;

import java.util.ArrayList;
import java.util.List;
public class Customer {
    private final CustomerId customerId;
    private final Name name;
    private List<Address> addressList;
    private List<PaymentMethod> paymentMethods;
    public Customer(CustomerId customerId, Name name, Address address) {
        this.customerId = customerId;
        this.name = name;
        this.addressList = new ArrayList<>();
        this.addressList.add(address);
    }

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
}
