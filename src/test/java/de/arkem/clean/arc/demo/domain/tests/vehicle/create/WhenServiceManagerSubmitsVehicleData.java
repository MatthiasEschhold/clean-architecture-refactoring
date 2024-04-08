package de.arkem.clean.arc.demo.domain.tests.vehicle.create;

import com.tngtech.jgiven.Stage;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.LicensePlate;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vehicle;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vin;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.mileage.record.Mileage;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.interactor.CreateVehicleInteractor;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.out.SaveVehicle;

public class WhenServiceManagerSubmitsVehicleData extends Stage<WhenServiceManagerSubmitsVehicleData> {
    Vehicle savedVehicle;

    public WhenServiceManagerSubmitsVehicleData service_manager_submits_vehicle_data(Vin vin, LicensePlate licensePlate, Mileage mileage, SaveVehicle saveVehicle) {
        CreateVehicleInteractor interactorUnderTest = new CreateVehicleInteractor(saveVehicle);
        savedVehicle = interactorUnderTest.create(vin, licensePlate, mileage);
        return self();
    }

    public Vehicle getSavedVehicle() {
        return savedVehicle;
    }
}
