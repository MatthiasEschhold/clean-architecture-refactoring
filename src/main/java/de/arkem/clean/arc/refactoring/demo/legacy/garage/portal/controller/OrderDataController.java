package de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller;

import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller.mapper.CustomerToOrderDataDboMapper;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller.mapper.OrderResourceToOrderDataDboMapper;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller.resource.CreateOrderRequest;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller.resource.OrderResource;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database.OrderDataDbo;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.service.OrderDataService;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.Customer;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.domain.model.CustomerId;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.usecase.in.CreateCustomer;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.customer.usecase.in.GetCustomer;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.LicensePlate;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vehicle;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.Vin;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.domain.model.mileage.record.Mileage;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.in.CreateVehicle;
import de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.usecase.in.GetVehicleByVin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static java.util.stream.Collectors.toList;
@Controller
public class OrderDataController {
    private static final Logger logger = LoggerFactory.getLogger(OrderDataController.class);
    private final OrderDataService orderDataService;
    private final CreateVehicle  createVehicle;
    private final GetVehicleByVin getVehicleByVin;
    private CreateCustomer createCustomer;
    private GetCustomer getCustomer;
    private final OrderResourceToOrderDataDboMapper orderMapper;
    private final CustomerToOrderDataDboMapper customerMapper;

    public OrderDataController(OrderDataService orderDataService, CreateVehicle createVehicle, GetVehicleByVin getVehicleByVin, CreateCustomer createCustomer, GetCustomer getCustomer, OrderResourceToOrderDataDboMapper orderMapper, CustomerToOrderDataDboMapper customerMapper) {
        this.orderDataService = orderDataService;
        this.createVehicle = createVehicle;
        this.getVehicleByVin = getVehicleByVin;
        this.createCustomer = createCustomer;
        this.getCustomer = getCustomer;
        this.orderMapper = orderMapper;
        this.customerMapper = customerMapper;
    }

    @GetMapping("/")
    public String getAllOrders(Model model) {
        List<OrderResource> orders = orderDataService.getAllOrders().stream().map(orderMapper::mapDboToResource).collect(toList());
        orders.forEach(o -> this.enrichWithVehicle(o, getVehicleByVin.get(new Vin(o.getVehicleId()))));
        model.addAttribute("orderList", orders);
        return "index";
    }
    @GetMapping("/showOrderDetails/{id}")
    public String getOrderDetails(@PathVariable(value = "id") int orderNumber, Model model) {
        OrderResource order = orderMapper.mapDboToResource(orderDataService.readOrder(orderNumber));
        enrichWithVehicle(order, getVehicleByVin.get(new Vin(order.getVehicleId())));
        logger.info("Order data with order number found: " + orderNumber);
        model.addAttribute("order", order);
        return "orderDetails";
    }
    @GetMapping("/createOrder")
    public String createOrder(Model model) {
        model.addAttribute("orderRequest", new CreateOrderRequest());
        return "/createNewOrderForm";
    }
    @PostMapping("/createOrder")
    public String createNewOrder(@ModelAttribute("orderRequest") CreateOrderRequest orderRequest) {
        Vehicle vehicle = createOrGetVehicle(orderRequest);
        Customer customer = createOrGetCustomer(orderRequest);
        OrderResource orderData = orderMapper.mapDboToResource(
                orderDataService.createOrder(orderMapper.mapResourceToDbo(orderRequest.getOrderData()), orderRequest.getCustomerId())
        );
        enrichWithVehicle(orderData, vehicle);
        enrichResourceWithCustomer(orderData, customer);
        return "redirect:/showOrderDetails/" + orderData.getId();
    }

    private Customer createOrGetCustomer(CreateOrderRequest orderRequest) {
        try {
            CustomerId customerId = new CustomerId(orderRequest.getCustomerId());
            return getCustomer.get(customerId);
        } catch (Exception e) {
            return createCustomer.create(customerMapper.mapToCustomer(orderRequest.getCustomerId(), orderRequest.getOrderData()));
        }
    }

    private Vehicle createOrGetVehicle(CreateOrderRequest orderRequest) {
        try {
            return getVehicleByVin.get(new Vin(orderRequest.getOrderData().getVehicleId()));
        } catch (Exception e) {
            return createVehicle.create(new Vin(orderRequest.getOrderData().getVehicleId()),
                    new LicensePlate(orderRequest.getOrderData().getLicensePlate()),
                    new Mileage(orderRequest.getOrderData().getId()));
        }
    }

    private void enrichWithVehicle(OrderResource order, Vehicle vehicle) {
        order.setMileage(vehicle.findLatestMileage().orElse(new Mileage(0.0)).value());
        order.setLicensePlate(vehicle.getLicensePlate().value());
    }

    private void enrichDboWithCustomer(OrderDataDbo dbo, Customer customer) {
        dbo.setCustomerName(customer.getName().firstname());
        dbo.setLastName(customer.getName().lastname());
        dbo.setStreet(customer.getAddressList().get(0).street());
        dbo.setPostalCode(customer.getAddressList().get(0).street());
    }

    private void enrichResourceWithCustomer(OrderResource resource, Customer customer) {
        resource.setCustomerName(customer.getName().firstname());
        resource.setLastName(customer.getName().lastname());
        resource.setStreet(customer.getAddressList().get(0).street());
        resource.setPostalCode(customer.getAddressList().get(0).street());
    }
}
