package com.example.daoimpl;

import com.example.dao.CinemaDao;
import com.example.entity.CinemaEntity;
import com.example.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CinemaDaoImpl implements CinemaDao {
    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public CinemaEntity save(CinemaEntity cinema) {
        return cinemaRepository.save(cinema);
    }

    @Override
    public void deleteById(int id) {
        cinemaRepository.deleteById(id);
    }

    @Override
    public Optional<CinemaEntity> findById(int id) {
        return cinemaRepository.findById(id);
    }

    @Override
    public List<CinemaEntity> findAll() {
        return cinemaRepository.findAll();
    }

    @Override
    public List<CinemaEntity> findByName(String name) {
        return cinemaRepository.findByName(name);
    }

    @Override
    public List<CinemaEntity> findByAddress(String address) {
        return cinemaRepository.findByAddress(address);
    }
}
