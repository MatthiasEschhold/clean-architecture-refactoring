package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model;

public record LicensePlate(String value) {
    private static final String EU_LICENSE_PLATE_PATTERN = "^[A-Z]{1,3}[a-z]{0,1}[-]{0,1}[A-Z]{0,2}\\s[0-9]{1,5}(\\s){0,1}[A-Z]{0,1}[a-z]{0,2}$";
    private static final String AUSTRIA_LICENSE_PLATE_PATTERN = "^[A-Z]{1}[a-z]{0,1}\\s[0-9]{1,5}\\s[A-Z]{1}[a-z]{0,2}$";
    private static final String GERMANY_LICENSE_PLATE_PATTERN = "^[A-Z]{1,3}-[A-Z]{1,2}\\s[0-9]{1,4}$";


    public LicensePlate {
        validateLicensePlate(value);
    }

    private void validateLicensePlate(String value) {
        if (value == null || !value.matches(EU_LICENSE_PLATE_PATTERN)) {
            throw new IllegalArgumentException("license plate is not valid");
        }
    }

}
