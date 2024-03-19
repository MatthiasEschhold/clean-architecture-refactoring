package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.in;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vehicle;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vin;

import java.util.Optional;

public interface GetVehicleByVin {
    Optional<Vehicle> get(Vin vin);
}
