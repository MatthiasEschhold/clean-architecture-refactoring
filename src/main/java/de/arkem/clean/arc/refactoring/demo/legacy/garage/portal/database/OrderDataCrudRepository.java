package de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDataCrudRepository extends CrudRepository<OrderDataDbo, Integer> {

}
