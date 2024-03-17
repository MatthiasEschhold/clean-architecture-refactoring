package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.out;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vehicle;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vin;

public interface FindVehicle {
    Vehicle findByVin(Vin vin);
}
