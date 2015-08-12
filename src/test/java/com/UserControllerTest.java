package com;

import com.entity.User;
import com.repository.UserRepository;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by dqf on 2015/8/12.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void setUp(){
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void shouldSaveNewUser(){
        final User savedUser = stubRepositoryToReturnUserOnSave();
        final User user = new User();
        final User returnedUser = userService.save(user);
        assertEquals("Return user should come from the repository",savedUser,returnedUser);
    }

    private User stubRepositoryToReturnUserOnSave(){
        User user = new User();
        when(userRepository.save(Matchers.any(User.class))).thenReturn(user);
        return user;
    }
}





























