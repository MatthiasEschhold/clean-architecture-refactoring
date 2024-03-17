package de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller;

import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database.OrderDataDbo;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.service.OrderDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderDataController {
    private static final Logger logger = LoggerFactory.getLogger(OrderDataController.class);
    private final OrderDataService orderDataService;

    public OrderDataController(OrderDataService orderDataService) {
        this.orderDataService = orderDataService;
    }

    @GetMapping("/")
    public String getAllOrders(Model model) {
        List<OrderDataDbo> orders = orderDataService.getAllOrders();
        model.addAttribute("orderList", orders);
        return "index";
    }

    @GetMapping("/showOrderDetails/{id}")
    public String getOrderDetails(@PathVariable(value = "id") int orderNumber, Model model) {
        OrderDataDbo order = orderDataService.readOrder(orderNumber);
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
        OrderDataDbo orderData = orderDataService.createOrder(orderRequest);
        return "redirect:/showOrderDetails/" + orderData.getId();
    }


}
