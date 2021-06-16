package com.example.adminService.repo;

import com.example.adminService.domain.Hotels;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HotelsRepo extends CrudRepository<Hotels, Long> {
    List<Hotels> findByTag(String tag);
}
