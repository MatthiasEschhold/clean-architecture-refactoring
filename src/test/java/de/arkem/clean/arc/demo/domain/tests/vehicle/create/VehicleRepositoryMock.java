package de.arkem.clean.arc.demo.domain.tests.vehicle.create;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vehicle;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vin;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.out.FindVehicle;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.out.SaveVehicle;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
public class VehicleRepositoryMock implements SaveVehicle, FindVehicle {

    public Vehicle vehicle;


    public Vehicle save(Vehicle vehicle) {
        this.vehicle = vehicle;
        return vehicle;
    }


    public Optional<Vehicle> findByVin(Vin vin) {
        if(vehicle != null && vehicle.getVin().equals(vin)) {
            return Optional.of(vehicle);
        }
        return Optional.empty();
    }
}
