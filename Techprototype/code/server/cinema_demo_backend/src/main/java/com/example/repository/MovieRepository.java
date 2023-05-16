package com.example.repository;

import com.example.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
    List<MovieEntity> findByTitle(String title);
    List<MovieEntity> findByGenre(String genre);
    List<MovieEntity> findByDirector(String director);
}