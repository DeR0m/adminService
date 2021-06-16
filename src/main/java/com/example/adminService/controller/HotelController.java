package com.example.adminService.controller;

import com.example.adminService.domain.Hotels;
import com.example.adminService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public ResponseEntity getHotels() {
        try {
            return ResponseEntity.ok(hotelService.getHotels());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Hotels hotels) {
        try {
            hotelService.create(hotels);
            return ResponseEntity.ok("Запись создана");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка при создании записи");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Hotels hotelsForDb,
                                 @RequestBody Hotels hotels) {
        try {
            hotelService.update(hotelsForDb, hotels);
            return ResponseEntity.ok("Запись изменина");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка при создание записи");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(hotelService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка при удалении записи");
        }
    }
}
