package com.example.hello.Controller;

import com.example.hello.Model.User;
import com.example.hello.Service.EmailService;
import com.example.hello.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    // Tạo mới người dùng
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Lấy tất cả người dùng
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Lấy người dùng theo ID
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Cập nhật thông tin người dùng
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
        User updatedUser = userService.updateUser(userId, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Xóa người dùng
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // API reset password
    @PostMapping("/{userId}/reset-password")
    public ResponseEntity<String> resetPassword(@PathVariable Long userId) {
        Optional<User> userOptional = userService.getUserById(userId);
        
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>("Người dùng không tồn tại!", HttpStatus.NOT_FOUND);
        }

        User user = userOptional.get();
        
        // Tạo mật khẩu mới
        String newPassword = generateRandomPassword(5);

        // Cập nhật mật khẩu mới cho user mà không mã hóa
        user.setPassword(newPassword);
        userService.updateUser(user.getUserId(), user);

        try {
            // Gửi email với mật khẩu mới
            emailService.sendEmail(user.getEmail(), "Reset mật khẩu", 
                "Mật khẩu mới của bạn là: " + newPassword);
        } catch (MailAuthenticationException e) {
            // Lỗi xác thực tài khoản email
            return new ResponseEntity<>("Lỗi xác thực email. Vui lòng kiểm tra thông tin đăng nhập email.", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (MailSendException e) {
            // Lỗi gửi email (có thể do kết nối mạng hoặc lỗi máy chủ SMTP)
            return new ResponseEntity<>("Không thể gửi email. Kiểm tra kết nối mạng hoặc máy chủ SMTP.", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (MessagingException e) {
            // Lỗi khác liên quan đến cấu hình email
            return new ResponseEntity<>("Lỗi trong quá trình gửi email. Vui lòng kiểm tra cấu hình email.", HttpStatus.INTERNAL_SERVER_ERROR);
        } 

        return new ResponseEntity<>("Mật khẩu mới đã được gửi qua email!", HttpStatus.OK);
    }

    // Phương thức tạo mật khẩu ngẫu nhiên
    private String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
