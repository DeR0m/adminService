package com.example.adminService.controller;

import com.example.adminService.domain.Flights;
import com.example.adminService.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PreAuthorize("hasAuthority('ADMIN') or hasRole('TOUROPERATOR')")
    @GetMapping
    public ResponseEntity getFlights() {
        try {
            return ResponseEntity.ok(flightService.getFlights());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasRole('TOUROPERATOR')")
    @GetMapping("/filter")
    public ResponseEntity filterByTag(@RequestParam(required = false, defaultValue = "") String filter) {
        try {
            return ResponseEntity.ok(flightService.filterByTag(filter));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка при поиски записи");
        }
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasRole('TOUROPERATOR')")
    @PostMapping
    public ResponseEntity create(@RequestBody Flights flights) {
        try {
            flightService.create(flights);
            return ResponseEntity.ok("Запись сделана");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка при создание записи");
        }
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasRole('TOUROPERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Flights flightsForDb,
                                 @RequestBody Flights flights) {
        try {
            flightService.update(flightsForDb, flights);
            return ResponseEntity.ok("Запись изменина");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка при изменении записи");
        }
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasRole('TOUROPERATOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(flightService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка при удалении записи");
        }
    }
}
