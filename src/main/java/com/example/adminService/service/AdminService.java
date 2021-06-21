package com.example.adminService.service;


import com.example.adminService.domain.Role;
import com.example.adminService.domain.User;
import com.example.adminService.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminService {
    @Autowired
    private UserRepo userRepo;

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    public User findUsername(String username) {
        return userRepo.findByUsername(username);
    }



    public Long deleteUser(Long id) {
        userRepo.findById(id);
        return id;
    }
}
