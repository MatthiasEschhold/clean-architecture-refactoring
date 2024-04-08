Feature: a vehicle can be created
  Scenario: client makes call to the use case CreateVehicle.create
    Given vehicle data vehicle identification number, license plate and mileage
    When the service manager submits data for a new vehicle
    Then then vehicle master data will be fetched from the master data service
    And the vehicle will be created in the database
