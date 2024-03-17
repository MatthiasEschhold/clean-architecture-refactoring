package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.adapter.out.db;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.adapter.out.db.model.MileageRecordJpaEntity;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.adapter.out.db.model.VehicleJpaEntity;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.LicensePlate;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vehicle;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vin;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.mileage.record.Mileage;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.mileage.record.MileageRecord;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.mileage.record.RecordDate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
class VehicleToVehicleJpaEntityMapper {
    public Vehicle mapToVehicle(VehicleJpaEntity jpaEntity)    {
        List<MileageRecord> mileageRecords = jpaEntity.getMileageRecords().stream()
                .map(entity -> new MileageRecord(new Mileage(entity.getMileage()), new RecordDate(entity.getLocalDateTime())))
                .collect(Collectors.toList());
        return Vehicle.createVehicleWithMileageRecords(new Vin(jpaEntity.getVin()),
                new LicensePlate(jpaEntity.getLicensePlate()),mileageRecords);
    }

    public VehicleJpaEntity mapToVehicleJpaEntity(Vehicle vehicle) {
        VehicleJpaEntity jpaEntity = new VehicleJpaEntity();
        jpaEntity.setVin(vehicle.getVin().value());
        jpaEntity.setLicensePlate(vehicle.getLicensePlate().value());
        jpaEntity.setMileageRecords(vehicle.getMileageRecords().stream()
                .map(mileageRecord -> {
                    MileageRecordJpaEntity mileageRecordJpaEntity = new MileageRecordJpaEntity();
                    mileageRecordJpaEntity.setLocalDateTime(mileageRecord.recordDate().value());
                    mileageRecordJpaEntity.setMileage(mileageRecord.mileage().value());
                    return mileageRecordJpaEntity;
                })
                .collect(Collectors.toList()));
        return jpaEntity;
    }
}
