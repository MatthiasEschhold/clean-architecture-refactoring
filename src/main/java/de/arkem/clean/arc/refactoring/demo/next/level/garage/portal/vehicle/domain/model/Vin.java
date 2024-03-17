package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model;

public record Vin(String value) {

    private static final String VIN_PATTERN = "(?=.*\\d|=.*[A-Z])(?=.*[A-Z])[A-Z0-9]{17}";

    public Vin {
        if (value == null || !value.matches(VIN_PATTERN)) {
            throw new IllegalArgumentException("vin is not valid");
        }
    }
}
