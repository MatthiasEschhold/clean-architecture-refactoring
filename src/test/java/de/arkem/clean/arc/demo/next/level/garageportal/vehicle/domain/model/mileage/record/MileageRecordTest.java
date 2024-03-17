package de.arkem.clean.arc.demo.next.level.garageportal.vehicle.domain.model.mileage.record;

import de.arkem.clean.arc.demo.next.level.garageportal.vehicle.domain.model.data.VehicleTestDataFactory;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.mileage.record.Mileage;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.mileage.record.MileageRecord;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.mileage.record.RecordDate;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MileageRecordTest {

    @Test
    void shouldCreateMileageRecord() {
        MileageRecord mileageRecord = VehicleTestDataFactory.createMileageRecord(1000, 0);
        assertEquals(1000, mileageRecord.mileage().value());
        assertNotNull(mileageRecord.recordDate());
    }

    @Test
    void shouldThrowExceptionWhenMileageIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new MileageRecord(null, new RecordDate(LocalDateTime.now())));
    }

    @Test
    void shouldThrowExceptionWhenRecordDateIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new MileageRecord(new Mileage(1000), null));
    }
}