package com.example.hello.Service;

import com.example.hello.Model.User;
import com.example.hello.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Tạo mới người dùng
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Lấy tất cả người dùng
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Lấy người dùng theo id (trả về Optional<User>)
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    // Cập nhật thông tin người dùng
    public User updateUser(Long userId, User user) {
        user.setUserId(userId);
        return userRepository.save(user);
    }

    // Xóa người dùng
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    // Tìm người dùng theo username (trả về Optional<User>)
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Tìm người dùng theo email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
