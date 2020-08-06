package com.softserve.edu.service.impl;

import com.softserve.edu.exception.EntityNotFoundException;
import com.softserve.edu.model.Role;
import com.softserve.edu.repository.RoleRepository;
import com.softserve.edu.service.RoleService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No role /w id " + id)));
    }

    @Override
    @Transactional
    public List<Role> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.isEmpty() ? new ArrayList<>() : roles;
    }

    @Override
    public Role createOrUpdate(Role role) {

        if (role.getId() != null) {

            Optional<Role> roleOptional = roleRepository.findById(role.getId());

            if (roleOptional.isPresent()) {
                Role newRole = roleOptional.get();
                newRole.setName(role.getName());
                return roleRepository.save(newRole);
            }
        }

        return roleRepository.save(role);
    }

    @Override
    public void deleteRoleById(Long id) {
        Optional<Role> role = roleRepository.findById(id);

        if (role.isPresent()) {
            roleRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("No role exist for given id");
        }
    }
}
