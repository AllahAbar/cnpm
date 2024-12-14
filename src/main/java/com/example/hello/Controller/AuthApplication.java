package com.example.hello.Controller;

import com.example.hello.Model.User;
import com.example.hello.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthApplication {

    @Autowired
    private UserRepository userRepository;

    // Lớp LoginRequest để nhận dữ liệu từ frontend
    public static class LoginRequest {
        private String username;
        private String password;
        
        // Getters và Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
        // Tìm người dùng trong cơ sở dữ liệu theo username
        Optional<User> optionalUser = userRepository.findByUsername(loginRequest.getUsername());

        // Nếu không tìm thấy người dùng
        if (!optionalUser.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Invalid username or password");
            return ResponseEntity.status(401).body(response); // Trả về mã lỗi 401 nếu username không đúng
        }

        // Lấy người dùng từ Optional
        User user = optionalUser.get();

        // So sánh mật khẩu từ request với mật khẩu trong cơ sở dữ liệu
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Invalid username or password");
            return ResponseEntity.status(401).body(response); // Trả về mã lỗi 401 nếu mật khẩu không đúng
        }

        // Nếu đăng nhập thành công, trả về thông tin người dùng (có thể gửi thêm thông tin khác nếu cần)
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("user", user);
        return ResponseEntity.ok(response); // Trả về 200 OK với thông tin người dùng
    }
}
