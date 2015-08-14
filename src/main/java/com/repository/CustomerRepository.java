package com.repository;

import com.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.*;

/**
 * Created by dqf on 2015/8/14.
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {
    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);
}
