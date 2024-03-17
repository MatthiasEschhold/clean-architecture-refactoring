package de.arkem.clean.arc.demo.next.level.garageportal.vehicle.domain.model;

import de.arkem.clean.arc.demo.next.level.garageportal.vehicle.domain.model.data.VehicleTestDataFactory;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.LicensePlate;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vehicle;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vin;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.mileage.record.Mileage;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VehicleTest {
    @Test
    void shouldUpdateMileageWithHigherValue() {
        Vehicle vehicle = VehicleTestDataFactory.createVehicle();
        vehicle.updateMileage(new Mileage(1000.01));
        assertThat(vehicle.getMileageRecords(), hasSize(2));
    }


    @Test
    void shouldUpdateMileageWithSameValue() {
        Vehicle vehicle = VehicleTestDataFactory.createVehicle();
        vehicle.updateMileage(new Mileage(1000.00));
        assertThat(vehicle.getMileageRecords(), hasSize(2));
    }

    @Test
    void shouldThrowExceptionDueToLowerMilegageThanThePrevious() {
        Vehicle vehicle = VehicleTestDataFactory.createVehicle();
        assertThrows(IllegalArgumentException.class, () -> vehicle.updateMileage(new Mileage(999.99)));
    }

    @Test
    void createNewVehicle() {
        Vehicle vehicle = VehicleTestDataFactory.createVehicle();
        assertThat(vehicle.getMileageRecords(), hasSize(1));
    }

    @Test
    void shouldFindLatestMileageWithValue3000() {
        Vehicle vehicle = VehicleTestDataFactory.createVehicle();
        assertThat(vehicle.findLatestMileage().isPresent(), is(true));
        assertThat(vehicle.findLatestMileage().get(), is(equalTo(new Mileage(1000))));
    }

}