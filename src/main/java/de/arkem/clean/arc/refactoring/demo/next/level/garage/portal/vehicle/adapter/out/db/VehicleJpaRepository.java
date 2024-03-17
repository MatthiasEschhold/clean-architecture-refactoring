package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.adapter.out.db;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.adapter.out.db.model.VehicleJpaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VehicleJpaRepository extends CrudRepository<VehicleJpaEntity, Long> {
    Optional<VehicleJpaEntity> findByVin(String value);
}
