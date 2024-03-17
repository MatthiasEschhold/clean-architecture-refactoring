package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.in;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vin;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.mileage.record.Mileage;

public interface UpdateMileage {
    void update(Vin vin, Mileage mileage);
}
