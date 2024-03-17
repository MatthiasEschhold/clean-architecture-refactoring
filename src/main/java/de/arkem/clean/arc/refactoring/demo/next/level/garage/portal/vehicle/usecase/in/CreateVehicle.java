package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.in;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.LicensePlate;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vehicle;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vin;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.mileage.record.Mileage;

public interface CreateVehicle {
    Vehicle create(Vin vin, LicensePlate licensePlate, Mileage mileage);
}
