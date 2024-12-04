package com.purplemango.gms.controller;

import com.purplemango.gms.models.clientdata.AddCustomer;
import com.purplemango.gms.models.clientdata.Customer;
import com.purplemango.gms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<Collection<Customer>> viewAllCustomers() {
        return ResponseEntity.ok(customerService.viewAllCustomers());

    }

//    @GetMapping("/{customer-id}")
    public ResponseEntity<Customer> viewCustomerById(@PathVariable("customer-id") String entityId) {
        return ResponseEntity.ok(customerService.viewCustomerById(entityId));
    }

    @GetMapping("/name/{customer-name}")
    public ResponseEntity<Customer> viewCustomerByUsername(@PathVariable("customer-name") String customerName) {
        return ResponseEntity.ok(customerService.viewCustomerByName(customerName));
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody AddCustomer entity) {
        customerService.createCustomer(entity);
        return ResponseEntity.ok("Customer created successfully");
    }



}
