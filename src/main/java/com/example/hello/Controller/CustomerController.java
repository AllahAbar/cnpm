package com.example.hello.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hello.Service.CustomerService;
import com.example.hello.Model.Customer;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // API lấy tất cả khách hàng
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // API lấy khách hàng theo ID
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable(value = "id") Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    // API tạo mới khách hàng
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    // API cập nhật thông tin khách hàng
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable(value = "id") Long customerId,
                                   @RequestBody Customer customerDetails) {
        return customerService.updateCustomer(customerId, customerDetails);
    }

    // API xóa khách hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable(value = "id") Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }
}
