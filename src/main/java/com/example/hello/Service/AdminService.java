package com.example.hello.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hello.Repository.AdminRepository;
import com.example.hello.ResourceNotFoundException.ResourceNotFoundException;
import com.example.hello.Model.Admin;


@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
    }

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Long id, Admin adminDetails) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin not found"));

        admin.setUsername(adminDetails.getUsername());
        admin.setPassword(adminDetails.getPassword());
        admin.setEmail(adminDetails.getEmail());
        admin.setRole(adminDetails.getRole());

        return adminRepository.save(admin);
    }

    public void deleteAdmin(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
        adminRepository.delete(admin);
    }
}