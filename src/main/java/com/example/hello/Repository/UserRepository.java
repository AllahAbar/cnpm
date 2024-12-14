package com.example.hello.Repository;

import com.example.hello.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Có thể thêm các query method nếu cần
    Optional<User> findByUsername(String username);
    // Tìm người dùng theo email
    Optional<User> findByEmail(String email);
}