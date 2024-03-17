package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.adapter.out.db.model;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "customer_tbl")
public class CustomerJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String lastname;
    private String firstname;
    @ElementCollection
    @CollectionTable(name = "adresses_tbl")
    private List<AdressJpaEntity> adresses;

    @ElementCollection
    @CollectionTable(name = "payment_methods_tbl")
    private List<PaymentMethodJpaEntity> paymentMethods;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public List<AdressJpaEntity> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<AdressJpaEntity> adresses) {
        this.adresses = adresses;
    }

    public List<PaymentMethodJpaEntity> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethodJpaEntity> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
}
