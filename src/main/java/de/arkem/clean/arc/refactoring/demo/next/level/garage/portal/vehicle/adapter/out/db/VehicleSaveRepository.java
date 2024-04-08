package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.adapter.out.db;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.adapter.out.db.model.VehicleJpaEntity;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vehicle;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.out.SaveVehicle;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
@Profile({"test", "default"})
@Component
public class VehicleSaveRepository implements SaveVehicle {
    private final VehicleJpaRepository vehicleJpaRepository;
    private final VehicleToVehicleJpaEntityMapper vehicleToVehicleJpaEntityMapper;

    public VehicleSaveRepository(VehicleJpaRepository vehicleJpaRepository, VehicleToVehicleJpaEntityMapper vehicleToVehicleJpaEntityMapper) {
        this.vehicleJpaRepository = vehicleJpaRepository;
        this.vehicleToVehicleJpaEntityMapper = vehicleToVehicleJpaEntityMapper;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        VehicleJpaEntity jpaEntity = vehicleToVehicleJpaEntityMapper.mapToVehicleJpaEntity(vehicle);
        VehicleJpaEntity savedJpaEntity = vehicleJpaRepository.save(jpaEntity);
        return vehicleToVehicleJpaEntityMapper.mapToVehicle(savedJpaEntity);
    }
}
