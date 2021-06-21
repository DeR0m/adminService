package com.example.adminService.controller;

import com.example.adminService.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //Есть проблемка
    @GetMapping("/username")
    public ResponseEntity getOneUser(@RequestParam String username) {
        try {
            return ResponseEntity.ok(adminService.findUsername(username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
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
