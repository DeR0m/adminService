package com.example.adminService.repo;

import com.example.adminService.domain.Flights;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FlightsRepo extends CrudRepository<Flights, Long> {
    List<Flights> findByTag(String tag);
}
