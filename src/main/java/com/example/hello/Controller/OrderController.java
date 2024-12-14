package com.example.hello.Controller;

import com.example.hello.Model.Order;
import com.example.hello.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Lấy tất cả các đơn hàng
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Lấy thông tin đơn hàng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long orderId) {
        Optional<Order> order = orderService.getOrderById(orderId);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Tạo mới một đơn hàng với nhân viên phụ trách
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order, @RequestParam Long staffId) {
        Order newOrder = orderService.createOrder(order, staffId);
        return newOrder != null ? ResponseEntity.status(HttpStatus.CREATED).body(newOrder) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Cập nhật thông tin đơn hàng với nhân viên phụ trách
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") Long orderId, @RequestBody Order orderDetails, @RequestParam Long staffId) {
        Order updatedOrder = orderService.updateOrder(orderId, orderDetails, staffId);
        return updatedOrder != null ? ResponseEntity.ok(updatedOrder) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Xóa thông tin đơn hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long orderId) {
        boolean isDeleted = orderService.deleteOrder(orderId);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
