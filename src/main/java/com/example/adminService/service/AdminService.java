package com.example.adminService.service;


import com.example.adminService.domain.Role;
import com.example.adminService.domain.User;
import com.example.adminService.exceptions.UserNotFoundException;
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

    public User findUsername(String username) throws UserNotFoundException {
        if (userRepo.findByUsername(username) == null && username.isEmpty()) {
            throw new UserNotFoundException("Пользователя с таким именем не существует");
        }
        return userRepo.findByUsername(username);
    }

    public User editProfile(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        return userRepo.save(user);
    }

    public Long deleteUser(Long id) {
        userRepo.findById(id);
        return id;
    }
}
