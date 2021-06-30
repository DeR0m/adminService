package com.example.adminService.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Hotels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private String tag;
}
