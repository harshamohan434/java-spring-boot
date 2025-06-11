package org.example.controllers;


import org.example.models.Role;
import org.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping
    public ResponseEntity<String> postRoles(@RequestBody List<Role> roles){
        boolean status = roleService.putRoles(roles);
        if (status){
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<String> getRoles(){
        List<Role> roles = roleService.getRoles();
        return new ResponseEntity<>(roles.toString(), HttpStatus.OK);
    }
}
