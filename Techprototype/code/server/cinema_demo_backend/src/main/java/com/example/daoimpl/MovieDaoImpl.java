package com.example.daoimpl;

import com.example.dao.MovieDao;
import com.example.entity.MovieEntity;
import com.example.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieDaoImpl implements MovieDao{
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public MovieEntity save(MovieEntity movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void deleteById(int id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Optional<MovieEntity> findById(int id) {
        return movieRepository.findById(id);
    }

    @Override
    public List<MovieEntity> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<MovieEntity> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<MovieEntity> findByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    @Override
    public List<MovieEntity> findByDirector(String director) {
        return movieRepository.findByDirector(director);
    }
}
