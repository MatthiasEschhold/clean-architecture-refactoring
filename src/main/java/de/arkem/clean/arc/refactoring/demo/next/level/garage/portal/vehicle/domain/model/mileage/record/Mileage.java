package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.mileage.record;

public record Mileage(double value) {
    public Mileage {
        validateMileage(value);
    }

    private void validateMileage(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("mileage is not valid");
        }
    }
}
