package com.example.repository;

import com.example.entity.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CinemaRepository extends JpaRepository<CinemaEntity, Integer> {
    List<CinemaEntity> findByName(String name);
    List<CinemaEntity> findByAddress(String address);
}
