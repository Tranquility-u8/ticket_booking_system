package com.example.dao;

import com.example.entity.ScreeningEntity;

import java.util.List;
import java.util.Optional;

public interface ScreeningDao {
    ScreeningEntity save(ScreeningEntity screening);
    void deleteById(int id);
    Optional<ScreeningEntity> findById(int id);
    List<ScreeningEntity> findAll();
    List<ScreeningEntity> findByCinemaId(int cinemaId);
    List<ScreeningEntity> findByMovieId(int movieId);
}