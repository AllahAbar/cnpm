package com.example.hello.Repository;

import org.springframework.stereotype.Repository;
import com.example.hello.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
