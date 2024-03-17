package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.interactor;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vehicle;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vin;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.exception.VehicleNotFoundException;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.mileage.record.Mileage;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.in.UpdateMileage;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.out.FindVehicle;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.out.SaveVehicle;

import java.util.Optional;

public class UpdateMileageInteractor implements UpdateMileage {

    private final SaveVehicle saveVehicle;
    private final FindVehicle findVehicle;

    public UpdateMileageInteractor(SaveVehicle saveVehicle, FindVehicle findVehicle) {
        this.saveVehicle = saveVehicle;
        this.findVehicle = findVehicle;
    }

    @Override
    public void update(Vin vin, Mileage mileage) {
        Vehicle vehicle = findVehicle.findByVin(vin)
                .orElseThrow(() -> new VehicleNotFoundException(vin));
        vehicle.updateMileage(mileage);
        saveVehicle.save(vehicle);
    }

}
