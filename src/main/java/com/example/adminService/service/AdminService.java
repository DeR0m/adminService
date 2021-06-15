package com.example.adminService.service;


import com.example.adminService.domain.Flights;
import com.example.adminService.repo.FlightsRepo;
import com.example.adminService.repo.HotelsRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private FlightsRepo flightsRepo;

    @Autowired
    private HotelsRepo hotelsRepo;

    public Iterable<Flights> getFlights(){
        return flightsRepo.findAll();
    }

    public Flights createFlights (Flights flights){
        return flightsRepo.save(flights);
    }

    public Flights editFlight(Flights flightsForDb,
                              Flights flights){
        BeanUtils.copyProperties(flights, flightsForDb, "id");
        return flightsRepo.save(flightsForDb);
    }

    public Long deleteFlight(Long id){
        flightsRepo.deleteById(id);
        return id;
    }

//    public Long deleteHotel(Long id){
//        hotelsRepo.deleteById(id);
//        return id;
//    }

}
