package com.example.dao;

import com.example.entity.CinemaEntity;

import java.util.List;
import java.util.Optional;

public interface CinemaDao {
    CinemaEntity save(CinemaEntity cinema);
    void deleteById(int id);
    Optional<CinemaEntity> findById(int id);
    List<CinemaEntity> findAll();
    List<CinemaEntity> findByName(String name);
    List<CinemaEntity> findByAddress(String address);
}