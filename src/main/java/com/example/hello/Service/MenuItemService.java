package com.example.hello.Service;

import com.example.hello.Model.MenuItem;
import com.example.hello.Repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    // Lấy tất cả menu items
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    // Lấy một menu item theo ID
    public Optional<MenuItem> getMenuItemById(Long id) {
        return menuItemRepository.findById(id);
    }

    // Tạo mới một menu item
    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    // Cập nhật một menu item
    public MenuItem updateMenuItem(Long id, MenuItem menuItem) {
        if (menuItemRepository.existsById(id)) {
            menuItem.setMenuItemId(id);
            return menuItemRepository.save(menuItem);
        }
        return null;
    }

    // Xóa một menu item
    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }
}
