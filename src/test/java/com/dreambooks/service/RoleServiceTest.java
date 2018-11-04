package com.dreambooks.service;

import com.dreambooks.model.Role;
import com.dreambooks.repository.RoleRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnAllRoles() {
        Role role = new Role();

        List<Role> rolesData = new LinkedList<>();
        rolesData.add(role);

        when(roleRepository.findAll()).thenReturn(rolesData);

        Set<Role> roles = roleService.getAllRoles();

        assertEquals(1, roles.size());
        verify(roleRepository, times(1)).findAll();
        verify(roleRepository, never()).findAllById(any());
    }

}