package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model;

public record LicensePlate(String value) {
    private static final String LICENSE_PLATE_PATTERN = "^[A-Z]{1,3}-[A-Z]{1,2}\\s[0-9]{1,4}$";

    public LicensePlate {
        if (value == null || !value.matches(LICENSE_PLATE_PATTERN)) {
            throw new IllegalArgumentException("license plate is not valid");
        }
    }
}
