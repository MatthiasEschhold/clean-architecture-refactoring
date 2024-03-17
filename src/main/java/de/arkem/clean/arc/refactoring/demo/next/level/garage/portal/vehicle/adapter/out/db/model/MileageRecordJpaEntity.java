package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.adapter.out.db.model;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class MileageRecordJpaEntity {
    private LocalDateTime localDateTime;
    private double mileage;

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }
}
