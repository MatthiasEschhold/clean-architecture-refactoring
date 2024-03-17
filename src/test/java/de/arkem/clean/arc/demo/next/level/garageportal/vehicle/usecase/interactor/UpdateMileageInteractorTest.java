package de.arkem.clean.arc.demo.next.level.garageportal.vehicle.usecase.interactor;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.LicensePlate;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vehicle;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vin;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.mileage.record.Mileage;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.interactor.UpdateMileageInteractor;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.out.FindVehicle;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.out.SaveVehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

class UpdateMileageInteractorTest {

    private static final String VIN_TEST_VALUE = "WP0ZZZ99ZTS392155";
    private static final String LICENSE_PLATE_TEST_VALUE = "ES-EL 0815";
    private static final double MILEAGE_TEST_VALUE = 1000;
    UpdateMileageInteractor updateMileageInteractor;
    SaveVehicle saveVehicle = Mockito.mock(SaveVehicle.class);
    FindVehicle findVehicle = Mockito.mock(FindVehicle.class);

    @BeforeEach
    void setUp() {
        updateMileageInteractor = new UpdateMileageInteractor(saveVehicle, findVehicle);
    }

    @Test
    void shouldUpdateMileageOfAVehicle() {
        Vin vin = new Vin(VIN_TEST_VALUE);
        LicensePlate licensePlate = new LicensePlate(LICENSE_PLATE_TEST_VALUE);
        Vehicle vehicle = Vehicle.createNewVehicle(vin, licensePlate, new Mileage(MILEAGE_TEST_VALUE));
        when(findVehicle.findByVin(vin)).thenReturn(vehicle);
        when(saveVehicle.save(vehicle)).thenReturn(vehicle);
        when(findVehicle.findByVin(vin)).thenReturn(vehicle);
        when(saveVehicle.save(vehicle)).thenReturn(vehicle);
        assertDoesNotThrow(() -> updateMileageInteractor.update(vin, new Mileage(2000)));
    }
}