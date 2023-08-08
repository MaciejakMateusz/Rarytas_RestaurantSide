package pl.rarytas.rarytas_restaurantside.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rarytas.rarytas_restaurantside.entity.RestaurantTable;
import pl.rarytas.rarytas_restaurantside.repository.RestaurantTableRepository;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/restaurantTables")
public class RestaurantTableRestController {
    private final RestaurantTableRepository restaurantTableRepository;

    public RestaurantTableRestController(RestaurantTableRepository restaurantTableRepository) {
        this.restaurantTableRepository = restaurantTableRepository;
    }

    @GetMapping
    public List<RestaurantTable> getAll() {
        return restaurantTableRepository.findAll();
    }

    @GetMapping("/{id}")
    public RestaurantTable getById(@PathVariable Integer id) {
        return restaurantTableRepository.findById(id).orElseThrow();
    }
}