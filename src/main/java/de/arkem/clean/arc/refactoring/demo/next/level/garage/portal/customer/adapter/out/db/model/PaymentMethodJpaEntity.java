package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.adapter.out.db.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class PaymentMethodJpaEntity {
    private String paymentMethod;
    private String identifier;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
