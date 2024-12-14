package com.example.hello.Service;

import com.example.hello.Model.Payment;
import com.example.hello.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Lấy tất cả Payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Lấy Payment theo ID
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    // Tạo mới Payment
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Xóa Payment theo ID
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    // Cập nhật Payment
    public Payment updatePayment(Long id, Payment payment) {
        if (paymentRepository.existsById(id)) {
            payment.setPaymentId(id);  // Set lại ID cho Payment khi cập nhật
            return paymentRepository.save(payment);
        } else {
            return null;  // Nếu không tìm thấy Payment, trả về null
        }
    }
}
