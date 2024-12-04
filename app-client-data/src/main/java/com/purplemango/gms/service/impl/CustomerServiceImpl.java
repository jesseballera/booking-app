package com.purplemango.gms.service.impl;

import com.purplemango.gms.config.TopicLibrary;
import com.purplemango.gms.messaging.MessageQueueService;
import com.purplemango.gms.models.clientdata.AddCustomer;
import com.purplemango.gms.models.clientdata.Address;
import com.purplemango.gms.models.clientdata.Customer;
import com.purplemango.gms.repository.CustomerRepository;
import com.purplemango.gms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    MessageQueueService messageQueueService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               @Qualifier("KafkaTemplate") MessageQueueService messageQueueService) {
        this.messageQueueService = messageQueueService;
        this.customerRepository = customerRepository;
    }
    @Override
    public void createCustomer(AddCustomer entity) {
//        customerRepository.findByName(entity.name()).orElseThrow(() -> new RuntimeException("Customer already exists"));
        Address address1 = new Address(UUID.randomUUID().toString(), entity.address().houseNumber(), entity.address().street(),
                entity.address().city(), entity.address().state(), entity.address().barangay(), entity.address().country(), entity.address().zipCode());
        Customer customer = new Customer(UUID.randomUUID().toString(), entity.firstName(), entity.lastName(), entity.phoneNumber(), entity.email(), address1);
        customerRepository.save(customer);
        messageQueueService.processMessage(TopicLibrary.CUSTOMER_ONBOARD, customer);
    }

    @Override
    public void updateCustomer(AddCustomer entity, String entityId) {

    }

    @Override
    public void deleteCustomer(String entityId) {
        viewCustomerById(entityId);
        customerRepository.deleteById(entityId);
    }

    @Override
    public Collection<Customer> viewAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer viewCustomerById(String entityId) {
        return customerRepository.findById(entityId).get();
//                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public Customer viewCustomerByName(String name) {
        return customerRepository.findCustomerByFirstNameOrLastName(name).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}
