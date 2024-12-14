package com.example.hello.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hello.Repository.CustomerRepository;
import com.example.hello.ResourceNotFoundException.ResourceNotFoundException;
import com.example.hello.Model.Customer;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Lấy tất cả khách hàng
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Lấy khách hàng theo ID
    public Customer getCustomerById(Long customerId) {
    return customerRepository.findByCustomerId(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + customerId));
}


    // Thêm mới khách hàng
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Cập nhật thông tin khách hàng
    public Customer updateCustomer(Long customerId, Customer customerDetails) {
        Customer existingCustomer = getCustomerById(customerId);
        existingCustomer.setName(customerDetails.getName());
        existingCustomer.setEmail(customerDetails.getEmail());
        existingCustomer.setPhonenumber(customerDetails.getPhonenumber());
        existingCustomer.setContractInfo(customerDetails.getContractInfo());
        return customerRepository.save(existingCustomer);
    }

    // Xóa khách hàng
    public void deleteCustomer(Long customerId) {
        Customer customer = getCustomerById(customerId);
        customerRepository.delete(customer);
    }
}
