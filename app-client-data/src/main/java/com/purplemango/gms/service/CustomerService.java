package com.purplemango.gms.service;

import com.purplemango.gms.models.clientdata.AddCustomer;
import com.purplemango.gms.models.clientdata.Customer;

import java.util.Collection;

public interface CustomerService {

    void createCustomer(AddCustomer entity);

    void updateCustomer(AddCustomer entity, String entityId);

    void deleteCustomer(String entityId);

    Collection<Customer> viewAllCustomers();

    Customer viewCustomerById(String entityId);

    Customer viewCustomerByName(String name);
}
