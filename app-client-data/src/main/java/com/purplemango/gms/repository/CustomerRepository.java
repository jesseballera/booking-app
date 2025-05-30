package com.purplemango.gms.repository;

import com.purplemango.gms.models.clientdata.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findCustomerByFirstNameOrLastName(String name);
}
