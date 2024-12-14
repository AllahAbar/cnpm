package com.example.hello.Controller;

import com.example.hello.Model.Restaurant;
import com.example.hello.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    // Lấy tất cả nhà hàng
    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    // Lấy nhà hàng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("id") Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(restaurantId);
        return restaurant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Tạo mới nhà hàng
    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant newRestaurant = restaurantService.createRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRestaurant);
    }

    // Cập nhật nhà hàng
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable("id") Long restaurantId, @RequestBody Restaurant restaurantDetails) {
        Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurantId, restaurantDetails);
        return updatedRestaurant != null ? ResponseEntity.ok(updatedRestaurant) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Xóa nhà hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable("id") Long restaurantId) {
        boolean isDeleted = restaurantService.deleteRestaurant(restaurantId);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
