package com.example.hello.Controller;

import com.example.hello.Model.Category;
import com.example.hello.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Đường dẫn lưu ảnh
    private final String UPLOAD_DIR = "src/main/resources/static/images/";

    // Lấy tất cả các danh mục
    @GetMapping
    public List<Category> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        
        // Thêm đường dẫn đầy đủ cho ảnh
        categories.forEach(category -> category.setImage("http://localhost:8080/images/" + category.getImage()));
        
        return categories;
    }

    // Lấy danh mục theo id
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            Category cat = category.get();
            // Thêm đường dẫn đầy đủ cho ảnh
            cat.setImage("http://localhost:8080/images/" + cat.getImage());
            return ResponseEntity.ok(cat);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Thêm danh mục mới với hình ảnh
    @PostMapping
    public ResponseEntity<Category> createCategory(
            @RequestParam("name") String name,   
            @RequestParam("image") MultipartFile imageFile) {
        try {
            // Lưu tệp hình ảnh
            String fileName = saveImage(imageFile);

            // Tạo đối tượng Category
            Category category = new Category();
            category.setName(name);
            category.setImage(fileName);

            Category createdCategory = categoryService.createCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Cập nhật danh mục với hình ảnh
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        try {
            Category category = categoryService.getCategoryById(id).orElse(null);
            if (category == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            // Cập nhật các thuộc tính
            category.setName(name);
            category.setDescription(description);

            if (imageFile != null && !imageFile.isEmpty()) {
                // Lưu tệp hình ảnh nếu có tệp mới
                String fileName = saveImage(imageFile);
                category.setImage(fileName);
            }

            Category updatedCategory = categoryService.updateCategory(id, category);
            return ResponseEntity.ok(updatedCategory);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Xóa danh mục
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    // Lưu hình ảnh vào thư mục images
    private String saveImage(MultipartFile imageFile) throws IOException {
        if (imageFile.isEmpty()) {
            throw new IOException("Tệp trống");
        }

        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            boolean created = uploadDir.mkdirs();
            System.out.println("Tạo thư mục: " + (created ? "Thành công" : "Thất bại"));
        }

        String originalFileName = imageFile.getOriginalFilename();
        String fileExtension = "";

        int dotIndex = originalFileName.lastIndexOf(".");
        if (dotIndex > 0) {
            fileExtension = originalFileName.substring(dotIndex);
        }

        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
        Path filePath = Paths.get(UPLOAD_DIR + uniqueFileName);

        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }
}
