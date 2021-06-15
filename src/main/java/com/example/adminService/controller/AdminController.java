package com.example.adminService.controller;

import com.example.adminService.domain.Flights;
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
    public ResponseEntity getFlights(){
        try{
            return ResponseEntity.ok(adminService.getFlights());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/flights")
    public ResponseEntity createFlights(@RequestBody Flights flights){
        try{
            adminService.createFlights(flights);
            return ResponseEntity.ok("Пост сделан");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity editFlight(@PathVariable("id") Flights flightsFromDb,
                                      @RequestBody Flights flights){
        try{
            adminService.editFlight(flightsFromDb, flights);
            return ResponseEntity.ok("Пост обнавлен");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFlight(@PathVariable Long id){
        try{
            return ResponseEntity.ok(adminService.deleteFlight(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
