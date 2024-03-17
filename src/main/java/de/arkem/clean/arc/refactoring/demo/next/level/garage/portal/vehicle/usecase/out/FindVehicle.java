package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.out;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vehicle;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vin;

import java.util.Optional;

public interface FindVehicle {
    Optional<Vehicle> findByVin(Vin vin);
}
