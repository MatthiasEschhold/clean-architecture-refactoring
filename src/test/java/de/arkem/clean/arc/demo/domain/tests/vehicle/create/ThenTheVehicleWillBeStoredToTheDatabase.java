package de.arkem.clean.arc.demo.domain.tests.vehicle.create;

import com.tngtech.jgiven.Stage;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vin;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.out.FindVehicle;

import static org.assertj.core.api.Assertions.assertThat;

public class ThenTheVehicleWillBeStoredToTheDatabase extends Stage<ThenTheVehicleWillBeStoredToTheDatabase> {

    public ThenTheVehicleWillBeStoredToTheDatabase vehicle_will_be_stored_to_the_database(Vin vin, FindVehicle findVehicle) {
        assertThat(findVehicle.findByVin(vin)).isPresent();
        return self();
    }

}
