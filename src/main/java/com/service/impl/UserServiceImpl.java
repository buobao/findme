package com.service.impl;

import com.entity.User;
import com.repository.UserRepository;
import com.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by dqf on 2015/8/12.
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Inject
    public UserServiceImpl(final UserRepository repository){
        this.repository = repository;
    }

    @Override
    @Transactional
    public User save(final User user){
        return repository.save(user);
    }
}
