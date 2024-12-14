package com.example.hello.Model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;  // Thêm import này để sử dụng @JsonIgnore
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;  // Trường ID cho Payment

    @ManyToOne(fetch = FetchType.LAZY)  // Lazy loading
    @JoinColumn(name = "order_id", nullable = false)
    @JsonIgnore  // Loại bỏ trường order khỏi JSON khi serialize
    private Order order;  // Quan hệ với bảng Order

    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;  // Kiểu dữ liệu BigDecimal cho số tiền

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "payment_time", nullable = false)
    private LocalDateTime paymentTime;

    @Column(name = "status", nullable = false)
    private String status;

    // Constructor không tham số
    public Payment() {}

    // Constructor có tham số (để tạo đối tượng dễ dàng hơn)
    public Payment(Order order, BigDecimal totalAmount, String paymentMethod, LocalDateTime paymentTime, String status) {
        this.order = order;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.paymentTime = paymentTime;
        this.status = status;
    }

    // Getter và Setter cho paymentId
    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    // Getter và Setter cho order
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    // Getter và Setter cho totalAmount
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    // Getter và Setter cho paymentMethod
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // Getter và Setter cho paymentTime
    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    // Getter và Setter cho status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Optional: Override toString(), equals(), và hashCode() nếu cần
}
