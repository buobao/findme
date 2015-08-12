package com.repository;

import com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dqf on 2015/8/12.
 */
public interface UserRepository extends JpaRepository<User, String> {
}
