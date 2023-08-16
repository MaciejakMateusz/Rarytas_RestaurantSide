package pl.rarytas.rarytas_restaurantside.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rarytas.rarytas_restaurantside.entity.Order;
import pl.rarytas.rarytas_restaurantside.service.OrderService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:8080")
public class OrderRestController {
    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllNotPaid() {
        return orderService.findAllNotPaid();
    }

    @GetMapping("/takeAway")
    public List<Order> getAllTakeAway() {
        return orderService.findAllTakeAway();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable Integer id) {
        return orderService.findById(id).orElseThrow();
    }

    @PostMapping
    public void saveOrder(@RequestBody Order order) {
        if (order.isForTakeAway()) {
            orderService.saveTakeAway(order);
            return;
        }
        orderService.save(order);
    }

    @PatchMapping
    public void updateOrder(@RequestBody Order order) {
        if (order.isForTakeAway()) {
            orderService.patchTakeAway(order);
            return;
        }
        orderService.patch(order);
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> options() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Allow", "GET, POST, PATCH, OPTIONS");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}

