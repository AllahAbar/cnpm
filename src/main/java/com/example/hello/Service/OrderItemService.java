package com.example.hello.Service;

import com.example.hello.Model.OrderItem;
import com.example.hello.Repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    // Lấy tất cả OrderItems
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    // Lấy OrderItem theo ID
    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    // Tạo mới OrderItem
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    // Xóa OrderItem theo ID
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }

    // Cập nhật OrderItem nếu tồn tại
    public OrderItem updateOrderItem(Long id, OrderItem orderItem) {
        if (orderItemRepository.existsById(id)) {
            orderItem.setOrderItemId(id);
            return orderItemRepository.save(orderItem);
        }
        return null;
    }
}
