package com.example.hello.Model;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    
    private String username;
    private String password;
    private String email;
    private String name;
    private String role;
    private String contact;  // Thay đổi kiểu dữ liệu của role từ Enum thành int

    // Constructor mặc định
    public User() {}

    // Constructor với tham số
    public User(String username, String password, String email, String name, String role, String contact) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.role = role;
        this.contact = contact;
    }

    // Getter và Setter cho userId
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // Getter và Setter cho username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter và Setter cho password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter và Setter cho email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter và Setter cho role
    public String getRole() {
        return role;
    }

    // Thay đổi kiểu tham số role từ Enum sang int
    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
