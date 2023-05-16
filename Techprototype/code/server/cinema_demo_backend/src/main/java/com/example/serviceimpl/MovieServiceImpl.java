package com.example.serviceimpl;

import com.example.dao.MovieDao;
import com.example.entity.MovieEntity;
import com.example.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieDao movieDao;

    @Override
    public MovieEntity save(MovieEntity movie) {
        // 可以在这里添加一些业务逻辑，例如验证电影信息是否合法等。
        return movieDao.save(movie);
    }

    @Override
    public void deleteById(int id) {
        movieDao.deleteById(id);
    }

    @Override
    public Optional<MovieEntity> findById(int id) {
        return movieDao.findById(id);
    }

    @Override
    public List<MovieEntity> findAll() {
        return movieDao.findAll();
    }

    @Override
    public List<MovieEntity> findByTitle(String title) {
        return movieDao.findByTitle(title);
    }

    @Override
    public List<MovieEntity> findByGenre(String genre) {
        return movieDao.findByGenre(genre);
    }

    @Override
    public List<MovieEntity> findByDirector(String director) {
        return movieDao.findByDirector(director);
    }
}