package com.example.repository;

import com.example.entity.ScreeningEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreeningRepository extends JpaRepository<ScreeningEntity, Integer> {
    List<ScreeningEntity> findByCinemaId(int cinemaId);
    List<ScreeningEntity> findByMovieId(int movieId);
}