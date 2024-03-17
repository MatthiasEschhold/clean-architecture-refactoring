package de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database;

import jakarta.persistence.Embeddable;

@Embeddable
public class OrderPositionDataDbo {

    private String positionId;
    private String positionDescription;
    private double quantity;

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionDescription() {
        return positionDescription;
    }

    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
