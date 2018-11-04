package com.dreambooks.service;

import com.dreambooks.model.Category;
import com.dreambooks.model.User;
import com.dreambooks.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnUserByLastName() {
        HashSet<User> users = new HashSet<>();
        user = new User();
        user.setLastName("LastName");
        users.add(user);

        when(userRepository.findUsersWithPartOfNames("LastName")).thenReturn(users);
        assertEquals(users, userService.getUsersByFirstAndLastName("LastName"));
    }

    @Test
    public void shouldDeleteUserById() {

        Long idToDelete = 1L;
        userService.deleteUser(idToDelete);

        verify(userRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    public void shouldReturnAllUsers() {
        List<User> userData = new LinkedList<>();
        userData.add(user);

        when(userRepository.findAll()).thenReturn(userData);

        Set<User> users = userService.getAllUsers();

        assertEquals(1, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void shouldReturnUserByEmail() {
        user = new User();
        user.setEmail("user@gmail.com");

        when(userRepository.findByEmail("user@gmail.com")).thenReturn(user);

        assertEquals(user, userService.findUserByEmail("user@gmail.com"));
    }

    @Test
    public void shouldReturnUserById() {
        user = new User();
        Optional<User> userOptional = Optional.of(user);

        when(userRepository.findById(1L)).thenReturn(userOptional);

        assertEquals(user, userService.getUserById(1L));
    }

    @Test
    public void shouldReturnMaxFixUsers() {
        Set<User> usersData = new HashSet<>();
        User user1 = new User(); user1.setId(1L); usersData.add(user1);
        User user2 = new User(); user2.setId(2L); usersData.add(user2);
        User user3 = new User(); user3.setId(3L); usersData.add(user3);
        User user4 = new User(); user4.setId(4L); usersData.add(user4);
        User user5 = new User(); user5.setId(5L); usersData.add(user5);

        when(userRepository.getMaxFiveNewUsers()).thenReturn(usersData);

        assertEquals(5, userService.getMaxFiveNewUsers().size());
    }

}