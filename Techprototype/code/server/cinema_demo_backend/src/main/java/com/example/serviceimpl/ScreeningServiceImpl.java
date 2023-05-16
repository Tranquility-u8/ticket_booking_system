package com.example.serviceimpl;

import com.example.dao.ScreeningDao;
import com.example.entity.ScreeningEntity;
import com.example.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScreeningServiceImpl implements ScreeningService {
    @Autowired
    private ScreeningDao screeningDao;

    @Override
    public ScreeningEntity save(ScreeningEntity screening) {
        // 可以在这里添加一些业务逻辑，例如验证放映信息是否合法等。
        return screeningDao.save(screening);
    }

    @Override
    public void deleteById(int id) {
        screeningDao.deleteById(id);
    }

    @Override
    public Optional<ScreeningEntity> findById(int id) {
        return screeningDao.findById(id);
    }

    @Override
    public List<ScreeningEntity> findAll() {
        return screeningDao.findAll();
    }

    @Override
    public List<ScreeningEntity> findByCinemaId(int cinemaId) {
        return screeningDao.findByCinemaId(cinemaId);
    }

    @Override
    public List<ScreeningEntity> findByMovieId(int movieId) {
        return screeningDao.findByMovieId(movieId);
    }
}