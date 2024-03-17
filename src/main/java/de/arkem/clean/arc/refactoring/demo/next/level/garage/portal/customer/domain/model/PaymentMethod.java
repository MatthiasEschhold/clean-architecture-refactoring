package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model;

public record PaymentMethod(PaymentMethodEnum paymentMethodEnum, String identifier) {
    public PaymentMethod {
        if (identifier == null) {
            throw new IllegalArgumentException("payment method requires an identifier");
        }
        if(paymentMethodEnum == null) {
            throw new IllegalArgumentException("payment method must not be null");
        }
    }
}
