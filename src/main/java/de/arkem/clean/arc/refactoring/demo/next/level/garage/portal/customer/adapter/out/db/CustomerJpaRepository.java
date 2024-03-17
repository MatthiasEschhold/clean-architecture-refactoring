package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.adapter.out.db;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.adapter.out.db.model.CustomerJpaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface CustomerJpaRepository extends CrudRepository<CustomerJpaEntity, Integer> {
}
