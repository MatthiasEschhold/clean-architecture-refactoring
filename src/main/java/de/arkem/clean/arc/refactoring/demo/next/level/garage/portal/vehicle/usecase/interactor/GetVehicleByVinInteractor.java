package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.interactor;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vehicle;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vin;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.in.GetVehicleByVin;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.out.FindVehicle;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetVehicleByVinInteractor implements GetVehicleByVin {

    private final FindVehicle findVehicle;

    public GetVehicleByVinInteractor(FindVehicle findVehicle) {
        this.findVehicle = findVehicle;
    }

    @Override
    public Optional<Vehicle> get(Vin vin) {
        return findVehicle.findByVin(vin);
    }
}
