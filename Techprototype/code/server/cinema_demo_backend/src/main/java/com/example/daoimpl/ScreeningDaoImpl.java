package com.example.daoimpl;

import com.example.dao.ScreeningDao;
import com.example.entity.ScreeningEntity;
import com.example.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ScreeningDaoImpl implements ScreeningDao {
    @Autowired
    private ScreeningRepository screeningRepository;

    @Override
    public ScreeningEntity save(ScreeningEntity screening) {
        return screeningRepository.save(screening);
    }

    @Override
    public void deleteById(int id) {
        screeningRepository.deleteById(id);
    }

    @Override
    public Optional<ScreeningEntity> findById(int id) {
        return screeningRepository.findById(id);
    }

    @Override
    public List<ScreeningEntity> findAll() {
        return screeningRepository.findAll();
    }

    @Override
    public List<ScreeningEntity> findByCinemaId(int cinemaId) {
        return screeningRepository.findByCinemaId(cinemaId);
    }

    @Override
    public List<ScreeningEntity> findByMovieId(int movieId) {
        return screeningRepository.findByMovieId(movieId);
    }
}