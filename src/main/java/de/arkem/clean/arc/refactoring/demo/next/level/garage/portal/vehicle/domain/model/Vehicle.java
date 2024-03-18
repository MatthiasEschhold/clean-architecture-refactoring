package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.mileage.record.Mileage;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.mileage.record.MileageRecord;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.mileage.record.RecordDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
public class Vehicle {
    private Vin vin;
    private LicensePlate licensePlate;
    private List<MileageRecord> mileageRecords;
    public static Vehicle createNewVehicle(Vin vin, LicensePlate licensePlate, Mileage mileage) {
        if (vin == null || licensePlate == null || mileage == null) {
            throw new IllegalArgumentException("vehicle data are not valid");
        }
        Vehicle vehicle = new Vehicle();
        vehicle.vin = vin;
        vehicle.licensePlate = licensePlate;
        vehicle.mileageRecords = new ArrayList<>();
        vehicle.mileageRecords.add(new MileageRecord(mileage, new RecordDate(LocalDateTime.now())));
        return vehicle;
    }
    public void updateMileage(Mileage mileage) {
        if (mileage != null && isNewMileageHigherThanThePreviousMileage(mileage)) {
            RecordDate recordDate = new RecordDate(LocalDateTime.now());
            mileageRecords.add(new MileageRecord(mileage, recordDate));
        } else {
            throw new IllegalArgumentException("mileage is not valid");
        }
    }
    public Optional<Mileage> findLatestMileage() {
        MileageRecord latestMileageRecord = mileageRecords.stream()
                .max(Comparator.comparing(mileageRecord -> mileageRecord.recordDate().value()))
                .orElse(null);
        if (latestMileageRecord == null) {
            return Optional.empty();
        }
        return Optional.of(latestMileageRecord.mileage());
    }

    public Vin getVin() {
        return vin;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public List<MileageRecord> getMileageRecords() {
        return mileageRecords;
    }

    private boolean isNewMileageHigherThanThePreviousMileage(Mileage mileage) {
        Optional<Mileage> latestMileageRecord = findLatestMileage();
        if (latestMileageRecord.isEmpty()) {
            return true;
        } else {
            if (latestMileageRecord.get().value() <= mileage.value()) {
                return true;
            }
        }
        return false;
    }

    private Vehicle() {}
}
