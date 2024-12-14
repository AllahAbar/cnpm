package com.example.hello.Service;

import com.example.hello.Model.Order;
import com.example.hello.Model.Staff;
import com.example.hello.Repository.OrderRepository;
import com.example.hello.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StaffRepository staffRepository;  // Thêm StaffRepository để tìm Staff

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public Order createOrder(Order order, Long staffId) {
        Optional<Staff> staff = staffRepository.findById(staffId);  // Lấy thông tin nhân viên từ staffId
        if (staff.isPresent()) {
            order.setStaff(staff.get());  // Gán nhân viên cho đơn hàng
            return orderRepository.save(order);
        }
        return null;  // Trả về null nếu không tìm thấy nhân viên
    }

    public Order updateOrder(Long orderId, Order orderDetails, Long staffId) {
        Optional<Order> existingOrder = orderRepository.findById(orderId);
        Optional<Staff> staff = staffRepository.findById(staffId);  // Lấy thông tin nhân viên từ staffId
        if (existingOrder.isPresent() && staff.isPresent()) {
            Order order = existingOrder.get();
            order.setOrderTime(orderDetails.getOrderTime());
            order.setOrderType(orderDetails.getOrderType());
            order.setStatus(orderDetails.getStatus());
            order.setStaff(staff.get());  // Cập nhật nhân viên cho đơn hàng
            return orderRepository.save(order);
        }
        return null;  // Trả về null nếu không tìm thấy đơn hàng hoặc nhân viên
    }

    public boolean deleteOrder(Long orderId) {
        Optional<Order> existingOrder = orderRepository.findById(orderId);
        if (existingOrder.isPresent()) {
            orderRepository.deleteById(orderId);
            return true;
        }
        return false;
    }
}
