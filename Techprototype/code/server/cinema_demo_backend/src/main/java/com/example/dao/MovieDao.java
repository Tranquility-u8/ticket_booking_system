package com.example.dao;

import com.example.entity.MovieEntity;

import java.util.List;
import java.util.Optional;

public interface MovieDao {
    MovieEntity save(MovieEntity movie);
    void deleteById(int id);
    Optional<MovieEntity> findById(int id);
    List<MovieEntity> findAll();
    List<MovieEntity> findByTitle(String title);
    List<MovieEntity> findByGenre(String genre);
    List<MovieEntity> findByDirector(String director);
}
