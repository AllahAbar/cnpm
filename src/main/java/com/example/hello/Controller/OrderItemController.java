package com.example.hello.Controller;

import com.example.hello.Model.OrderItem;
import com.example.hello.Service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    // Lấy tất cả OrderItems
    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    // Lấy OrderItem theo ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        Optional<OrderItem> orderItem = orderItemService.getOrderItemById(id);
        return orderItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tạo mới OrderItem
    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem createdOrderItem = orderItemService.createOrderItem(orderItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderItem);
    }

    // Cập nhật OrderItem
    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItem) {
        OrderItem updatedOrderItem = orderItemService.updateOrderItem(id, orderItem);
        return updatedOrderItem != null ? ResponseEntity.ok(updatedOrderItem) : ResponseEntity.notFound().build();
    }

    // Xóa OrderItem
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}
