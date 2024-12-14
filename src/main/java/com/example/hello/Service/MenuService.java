package com.example.hello.Service;

import com.example.hello.Model.Menu;
import com.example.hello.Repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Optional<Menu> getMenuById(Long menuId) {
        return menuRepository.findById(menuId);
    }

    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu updateMenu(Long menuId, Menu menuDetails) {
        Optional<Menu> existingMenu = menuRepository.findById(menuId);
        if (existingMenu.isPresent()) {
            Menu menu = existingMenu.get();
            menu.setValidFrom(menuDetails.getValidFrom());
            menu.setValidTo(menuDetails.getValidTo());
            return menuRepository.save(menu);
        }
        return null; // Trả về null nếu không tìm thấy
    }

    public boolean deleteMenu(Long menuId) {
        Optional<Menu> existingMenu = menuRepository.findById(menuId);
        if (existingMenu.isPresent()) {
            menuRepository.deleteById(menuId);
            return true;
        }
        return false;
    }
}

