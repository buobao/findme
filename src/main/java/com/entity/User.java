package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by dqf on 2015/8/12.
 */
@Entity
public class User {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @NotNull
    @Size(max=64)
    private String id;

    @Column(name = "name", nullable = false)
    @NotNull
    @Size(max=64)
    private String name;

    @Column(name = "password", nullable = false)
    @NotNull
    @Size(max=32)
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
