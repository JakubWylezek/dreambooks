package com.dreambooks.service;

import com.dreambooks.model.Role;
import com.dreambooks.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Set<Role> getAllRoles() {
        Set<Role> roles = new HashSet<>();
        roleRepository.findAll().iterator().forEachRemaining(roles::add);

        return roles;

    }
}
