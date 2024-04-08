package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.usecase.interactor;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.PaymentMethodEnum;

public record CreateCustomerCommand(int customerId, PaymentMethodEnum paymentMethod, String paymentIdentifier) {
}
