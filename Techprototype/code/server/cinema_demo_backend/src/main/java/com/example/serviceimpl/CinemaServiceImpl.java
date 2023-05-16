package com.example.serviceimpl;

import com.example.dao.CinemaDao;
import com.example.entity.CinemaEntity;
import com.example.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaServiceImpl implements CinemaService {
    @Autowired
    private CinemaDao cinemaDao;

    @Override
    public CinemaEntity save(CinemaEntity cinema) {
        // 可以在这里添加一些业务逻辑，例如验证影院信息是否合法等。
        return cinemaDao.save(cinema);
    }

    @Override
    public void deleteById(int id) {
        cinemaDao.deleteById(id);
    }

    @Override
    public Optional<CinemaEntity> findById(int id) {
        return cinemaDao.findById(id);
    }

    @Override
    public List<CinemaEntity> findAll() {
        return cinemaDao.findAll();
    }

    @Override
    public List<CinemaEntity> findByName(String name) {
        return cinemaDao.findByName(name);
    }

    @Override
    public List<CinemaEntity> findByAddress(String address) {
        return cinemaDao.findByAddress(address);
    }
}