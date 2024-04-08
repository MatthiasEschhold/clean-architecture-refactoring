package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.adapter.out.db;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.adapter.out.db.model.VehicleJpaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleJpaRepository extends CrudRepository<VehicleJpaEntity, Long> {
    Optional<VehicleJpaEntity> findByVin(String value);
}
