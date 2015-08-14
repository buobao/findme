package com.controller.example;

import com.entity.Customer;
import com.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by dqf on 2015/8/14.
 */
@Controller
public class MongoDBController {
    @Autowired
    private CustomerRepository repository;

    private List<Customer> customers;

    @ModelAttribute("customers")
    List<Customer> getCustomer(){return customers;}

    @RequestMapping("/customer")
    public String showCustomer(){
        customers = new ArrayList<Customer>();
        //this.repository.save(new Customer("Lebron", "James"));
        //this.repository.save(new Customer("Pual","Chris"));
        customers.addAll(this.repository.findAll().stream().collect(Collectors.toList()));
        //System.out.println(this.repository.findByFirstName("Lebron"));
        //System.out.println(this.repository.findByLastName("Chris"));
        //this.repository.findByFirstName("Alice");
        //this.repository.findByLastName("Smith")
        return "customer";
    }
}



























