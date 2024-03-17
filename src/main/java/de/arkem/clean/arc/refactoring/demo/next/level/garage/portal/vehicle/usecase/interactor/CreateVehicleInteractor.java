package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.interactor;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.LicensePlate;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vehicle;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vin;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.mileage.record.Mileage;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.in.CreateVehicle;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.out.SaveVehicle;
import org.springframework.stereotype.Component;

@Component
public class CreateVehicleInteractor implements CreateVehicle {
    private final SaveVehicle saveVehicle;

    public CreateVehicleInteractor(SaveVehicle saveVehicle) {
        this.saveVehicle = saveVehicle;
    }

    @Override
    public Vehicle create(Vin vin, LicensePlate licensePlate, Mileage mileage) {
        return saveVehicle.save(Vehicle.createNewVehicle(vin, licensePlate, mileage));
    }
}
