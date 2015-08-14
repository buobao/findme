package com.entity;

import javax.persistence.Id;

/**
 * Created by dqf on 2015/8/14.
 */
public class Customer {
    @Id
    private String id;

    private String firstName;
    private String lastName;

    public Customer(){}

    public  Customer(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        return String.format("Customer[id=%s, forstName='%s', lastName='%s']",id,firstName,lastName);
    }

}
