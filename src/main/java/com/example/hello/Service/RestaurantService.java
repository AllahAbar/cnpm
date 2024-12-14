package com.example.hello.Service;

import com.example.hello.Model.Restaurant;
import com.example.hello.Repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Long restaurantId, Restaurant restaurantDetails) {
        Optional<Restaurant> existingRestaurant = restaurantRepository.findById(restaurantId);
        if (existingRestaurant.isPresent()) {
            Restaurant restaurant = existingRestaurant.get();
            restaurant.setRestaurantName(restaurantDetails.getRestaurantName());
            restaurant.setLocation(restaurantDetails.getLocation());
            return restaurantRepository.save(restaurant);
        }
        return null; // Trả về null nếu không tìm thấy
    }

    public boolean deleteRestaurant(Long restaurantId) {
        Optional<Restaurant> existingRestaurant = restaurantRepository.findById(restaurantId);
        if (existingRestaurant.isPresent()) {
            restaurantRepository.deleteById(restaurantId);
            return true;
        }
        return false;
    }
}
