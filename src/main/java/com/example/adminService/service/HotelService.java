package com.example.adminService.service;

import com.example.adminService.domain.Hotels;
import com.example.adminService.repo.HotelsRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    @Autowired
    private HotelsRepo hotelsRepo;

    public Iterable<Hotels> getHotels() {
        return hotelsRepo.findAll();
    }

    public Hotels create(Hotels hotels) {
        return hotelsRepo.save(hotels);
    }

    public Hotels update(Hotels hotelsForDb, Hotels hotels) {
        BeanUtils.copyProperties(hotels, hotelsForDb, "id");
        return hotelsRepo.save(hotelsForDb);
    }

    public Iterable<Hotels> filterByTag(String filter) {
        Iterable<Hotels> hotels = hotelsRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            hotels = hotelsRepo.findByTag(filter);
        } else {
            hotels = hotelsRepo.findAll();
        }

        return hotels;
    }

    public Long delete(Long id) {
        hotelsRepo.deleteById(id);
        return id;
    }
}
