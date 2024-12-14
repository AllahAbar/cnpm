package com.example.hello.Controller;

import com.example.hello.Model.Menu;
import com.example.hello.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    // Lấy tất cả các menu
    @GetMapping
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    // Lấy thông tin menu theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable("id") Long menuId) {
        Optional<Menu> menu = menuService.getMenuById(menuId);
        return menu.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Tạo mới một menu
    @PostMapping
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) {
        Menu newMenu = menuService.createMenu(menu);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMenu);
    }

    // Cập nhật thông tin menu
    @PutMapping("/{id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable("id") Long menuId, @RequestBody Menu menuDetails) {
        Menu updatedMenu = menuService.updateMenu(menuId, menuDetails);
        return updatedMenu != null ? ResponseEntity.ok(updatedMenu) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Xóa menu
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable("id") Long menuId) {
        boolean isDeleted = menuService.deleteMenu(menuId);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
