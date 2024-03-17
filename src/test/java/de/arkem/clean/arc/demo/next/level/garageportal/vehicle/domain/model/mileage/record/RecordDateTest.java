package de.arkem.clean.arc.demo.next.level.garageportal.vehicle.domain.model.mileage.record;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.mileage.record.RecordDate;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecordDateTest {

    @Test
    void testRecordDate() {
        LocalDateTime date = LocalDateTime.now();
        RecordDate recordDate = new RecordDate(date);
        assertEquals(date, recordDate.value());
    }

    @Test
    void testRecordDateNull() {
        assertThrows(IllegalArgumentException.class, () -> new RecordDate(null));
    }

}