package de.arkem.clean.arc.demo.domain.tests.vehicle.create;

import com.tngtech.jgiven.junit5.ScenarioTest;
import org.junit.jupiter.api.Test;

public class CreateVehicleBddTest extends ScenarioTest<GivenVehicleDataState,
        WhenServiceManagerSubmitsVehicleData,
        ThenTheVehicleWillBeStoredToTheDatabase> {
    @Test
    public void should_create_vehicle() {
        given().vehicle_data_state();
        //when().service_manager_submits_vehicle_data(given().getVin(), given().getLicensePlate(), given().getMileage(), given().getVehicleRepositoryMock());
        //then().vehicle_will_be_stored_to_the_database(given().getVin(), given().getVehicleRepositoryMock());
    }
}
