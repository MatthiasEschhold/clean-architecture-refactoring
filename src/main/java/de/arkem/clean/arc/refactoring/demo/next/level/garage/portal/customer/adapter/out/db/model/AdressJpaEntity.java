package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.adapter.out.db.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class AdressJpaEntity {
    private String street;
    private String city;
    private String postalCode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
