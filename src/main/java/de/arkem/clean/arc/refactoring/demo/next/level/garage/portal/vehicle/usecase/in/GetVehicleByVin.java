package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.in;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vehicle;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vin;

public interface GetVehicleByVin {
    Vehicle get(Vin vin);
}
