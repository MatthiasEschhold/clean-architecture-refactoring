package de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller;

import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller.resource.OrderResource;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.service.OrderDataService;
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
    private final OrderResourceToDboMapper mapper;
    public OrderDataController(OrderDataService orderDataService, CreateVehicle createVehicle,
                               GetVehicleByVin getVehicleByVin, OrderResourceToDboMapper mapper) {
        this.orderDataService = orderDataService;
        this.createVehicle = createVehicle;
        this.getVehicleByVin = getVehicleByVin;
        this.mapper = mapper;
    }
    @GetMapping("/")
    public String getAllOrders(Model model) {
        List<OrderResource> orders = orderDataService.getAllOrders().stream().map(mapper::mapDboToResource).collect(toList());
        orders.forEach(this::enrichWithVehicle);
        model.addAttribute("orderList", orders);
        return "index";
    }
    private void enrichWithVehicle(OrderResource order) {
        Vehicle vehicle = getVehicleByVin.get(new Vin(order.getVehicleId()));
        order.setMileage(vehicle.findLatestMileage().orElse(new Mileage(0.0)).value());
        order.setLicensePlate(vehicle.getLicensePlate().value());
    }
    @GetMapping("/showOrderDetails/{id}")
    public String getOrderDetails(@PathVariable(value = "id") int orderNumber, Model model) {
        OrderResource order = mapper.mapDboToResource(orderDataService.readOrder(orderNumber));
        enrichWithVehicle(order);
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
        Vehicle vehicle = createVehicle(orderRequest);
        OrderResource orderData = mapper.mapDboToResource(
                orderDataService.createOrder(mapper.mapResourceToDbo(orderRequest.getOrderData()), orderRequest.getCustomerId())
        );
        enrichWithVehicle(orderData);
        return "redirect:/showOrderDetails/" + orderData.getId();
    }
    private Vehicle createVehicle(CreateOrderRequest orderRequest) {
        return createVehicle.create(new Vin(orderRequest.getOrderData().getVehicleId()),
                new LicensePlate(orderRequest.getOrderData().getLicensePlate()),
                new Mileage(orderRequest.getOrderData().getId()));
    }
}
