package com.example.lesson_4.service;

import com.example.lesson_4.dto.RoleDto;
import com.example.lesson_4.persist.Role;
import com.example.lesson_4.persist.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll()
                .stream().map(RoleServiceImpl::roleToDto)
                .collect(Collectors.toList());
    }

    private static RoleDto roleToDto(Role role) {
        return new RoleDto(role.getId(), role.getRoleName(), role.getUserList());
    }
}
