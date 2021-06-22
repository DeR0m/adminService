package com.example.adminService.controller;

import com.example.adminService.domain.User;
import com.example.adminService.exceptions.UserNotFoundException;
import com.example.adminService.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping
    public ResponseEntity getUser() {
        try {
            return ResponseEntity.ok(adminService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }


    @GetMapping("/username")
    public ResponseEntity getOneUser(@RequestParam(required = false, defaultValue = "") String username) {
        try {
            return ResponseEntity.ok(adminService.findUsername(username));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    //Надо смотреть запрос
    @PostMapping("/edit/{user}")
    public ResponseEntity editProfile(
            @PathVariable String username,
            @PathVariable User user,
            @RequestParam Map<String, String> form

    ) {
        try {
            return ResponseEntity.ok(adminService.editProfile(user, username, form));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибки");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(adminService.deleteUser(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибки при удалении пользователя");
        }
    }
}
