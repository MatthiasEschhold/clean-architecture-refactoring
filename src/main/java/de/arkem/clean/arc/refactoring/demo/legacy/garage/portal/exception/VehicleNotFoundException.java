package de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.exception;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vin;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(Vin vin) {
        super("vehicle with vin " + vin.value() + " not found");
    }
}
