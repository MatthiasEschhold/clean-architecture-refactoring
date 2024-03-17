package de.arkem.clean.arc.demo.next.level.garageportal.vehicle.usecase.interactor;

import de.arkem.clean.arc.demo.next.level.garageportal.vehicle.domain.model.data.VehicleTestDataFactory;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.LicensePlate;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vehicle;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vin;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.mileage.record.Mileage;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.interactor.CreateVehicleInteractor;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.out.SaveVehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateVehicleInteractorTest {
    CreateVehicleInteractor interactorUnderTest;
    SaveVehicle saveVehicle = Mockito.mock(SaveVehicle.class);

    @BeforeEach
    void setUp() {
        interactorUnderTest = new CreateVehicleInteractor(saveVehicle);
    }

    @Test
    void shouldCreateANewVehicle() {
        var licensePlate = new LicensePlate(VehicleTestDataFactory.LICENSE_PLATE_TEST_VALUE);
        var vin = new Vin(VehicleTestDataFactory.VIN_TEST_VALUE);
        var mileage = new Mileage(VehicleTestDataFactory.MILEAGE_TEST_VALUE);

        var savedVehicle = Vehicle.createNewVehicle(vin, licensePlate, mileage);

        when(saveVehicle.save(any(Vehicle.class))).thenReturn(savedVehicle);

        var vehicle = interactorUnderTest.create(vin, licensePlate, mileage);

        assertThat(vehicle.getVin(), equalTo(vin));
        assertThat(vehicle.getLicensePlate(), equalTo(licensePlate));
    }

}