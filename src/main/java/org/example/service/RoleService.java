package org.example.service;


import org.example.models.Role;
import org.example.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    public boolean putRoles(List<Role> roles){
        try{
            roleRepository.saveAll(roles);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
