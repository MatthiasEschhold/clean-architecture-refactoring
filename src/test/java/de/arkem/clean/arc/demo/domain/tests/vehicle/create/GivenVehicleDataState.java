package de.arkem.clean.arc.demo.domain.tests.vehicle.create;

import com.tngtech.jgiven.Stage;
import de.arkem.clean.arc.demo.next.level.garageportal.vehicle.domain.model.data.VehicleTestDataFactory;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.LicensePlate;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vin;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.mileage.record.Mileage;
public class GivenVehicleDataState extends Stage<GivenVehicleDataState> {
    LicensePlate licensePlate;
    Vin vin;
    Mileage mileage;
    VehicleRepositoryMock vehicleRepositoryMock;
    public GivenVehicleDataState vehicle_data_state() {
        licensePlate = new LicensePlate(VehicleTestDataFactory.LICENSE_PLATE_TEST_VALUE);
        vin = new Vin(VehicleTestDataFactory.VIN_TEST_VALUE);
        mileage = new Mileage(VehicleTestDataFactory.MILEAGE_TEST_VALUE);
        vehicleRepositoryMock = new VehicleRepositoryMock();
        return self();
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public Vin getVin() {
        return vin;
    }

    public Mileage getMileage() {
        return mileage;
    }

    public VehicleRepositoryMock getVehicleRepositoryMock() {
        return vehicleRepositoryMock;
    }
}
