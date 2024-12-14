package com.example.hello.Service;

import com.example.hello.Model.Category;
import com.example.hello.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Lấy tất cả các category
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Lấy category theo id
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Thêm category mới
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Cập nhật category
    public Category updateCategory(Long id, Category category) {
        if (categoryRepository.existsById(id)) {
            category.setId(id);
            return categoryRepository.save(category);
        }
        return null;
    }

    // Xóa category
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
