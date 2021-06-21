package com.example.adminService.service;

import com.example.adminService.domain.Flights;
import com.example.adminService.repo.FlightsRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

    @Autowired
    private FlightsRepo flightsRepo;

    public Iterable<Flights> getFlights() {
        return flightsRepo.findAll();
    }

    public Flights create(Flights flights) {
        return flightsRepo.save(flights);
    }

    public Flights update(Flights flightsForDb, Flights flights) {
        BeanUtils.copyProperties(flights, flightsForDb, "id");
        return flightsRepo.save(flightsForDb);
    }

    public Iterable<Flights> filterByTag(String filter){
        Iterable<Flights> flights = flightsRepo.findAll();

        if(filter != null && !filter.isEmpty()){
            flights = flightsRepo.findByTag(filter);
        }else {
            flights = flightsRepo.findAll();
        }

        return flights;
    }

    public Long delete(Long id) {
        flightsRepo.deleteById(id);
        return id;
    }
}
