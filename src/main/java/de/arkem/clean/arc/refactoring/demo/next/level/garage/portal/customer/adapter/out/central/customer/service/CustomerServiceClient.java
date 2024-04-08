package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.adapter.out.central.customer.service;

import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.Customer;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.CustomerId;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.usecase.out.FetchCustomer;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

//rearc
//service will be refactored to an adapter
@Component
public class CustomerServiceClient implements FetchCustomer {

    private final static String CUSTOMER_SERVICE_URL = "http://customer-service:8080";
    private RestTemplate customerClient;

    public CustomerServiceClient() {
        customerClient = new RestTemplate();
    }

    @Override
    public Customer fetch(CustomerId customerId) {
        return null;
    }

    public CustomerResponse getCustomer(int customerId) {
        //rearc
        //OrderUtil.validateCustomerId(customerId);
        CustomerResponse response = customerClient.getForObject(CUSTOMER_SERVICE_URL, CustomerResponse.class);   // Call customer service to get customer name
        //CustomerResponse response = createCustomerResponse();
        //OrderUtil.validateCustomerResponse(response);
        return response;
    }

    private CustomerResponse createCustomerResponse() {
        List<String> names = Arrays.asList("John Doe", "Jane Doe", "Matthias Maier", "Erika Schulze", "Andreas Schmidt", "Michael Schmitz", "Thomas Schneider", "Klaus Schreiner", "Uwe Schulte", "Ute Schulz");
        List<String> streets = Arrays.asList("Hauptstraße", "Bahnhofstraße", "Lindenstraße", "Bergstraße", "Ringstraße", "Kirchstraße", "Schulstraße", "Mühlenstraße", "Goethestraße", "Friedhofstraße");
        List<String> cities = Arrays.asList("Stuttgart", "München", "Berlin", "Hamburg", "Frankfurt", "Düsseldorf", "Dortmund", "Essen", "Bremen", "Hannover");
        int randomNumber = new Random().nextInt(names.size() - 1);
        String name = names.get(randomNumber);
        String[] nameParts = name.split(" ");
        CustomerResponse response = new CustomerResponse();
        response.setCustomerName(nameParts[0]);
        response.setCustomerLastName(nameParts[1]);
        int streetRandom = new Random().nextInt(streets.size() - 1);
        response.setStreet(streets.get(streetRandom) + " " + streetRandom);
        response.setPostalCode("7262" + streetRandom);
        response.setCity(cities.get(new Random().nextInt(cities.size() - 1)));
        return response;
    }

}
