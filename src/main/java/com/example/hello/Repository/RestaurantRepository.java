package com.example.hello.Repository;

import com.example.hello.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    // Có thể thêm các phương thức tìm kiếm tùy chỉnh nếu cần
}
