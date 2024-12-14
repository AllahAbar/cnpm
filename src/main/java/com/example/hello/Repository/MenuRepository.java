package com.example.hello.Repository;

import com.example.hello.Model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    // Bạn có thể thêm các phương thức tìm kiếm tùy chỉnh nếu cần
}
