package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.adapter.out.db;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vehicle;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vin;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.out.FindVehicle;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VehicleFindRepository implements FindVehicle {

    private final VehicleJpaRepository vehicleJpaRepository;
    private final VehicleToVehicleJpaEntityMapper vehicleToVehicleJpaEntityMapper;

    public VehicleFindRepository(VehicleJpaRepository vehicleJpaRepository, VehicleToVehicleJpaEntityMapper vehicleToVehicleJpaEntityMapper) {
        this.vehicleJpaRepository = vehicleJpaRepository;
        this.vehicleToVehicleJpaEntityMapper = vehicleToVehicleJpaEntityMapper;
    }

    @Override
    public Optional<Vehicle> findByVin(Vin vin) {
        return vehicleJpaRepository.findByVin(vin.value())
                .map(vehicleToVehicleJpaEntityMapper::mapToVehicle)
                .or(() -> Optional.empty());
    }
}
