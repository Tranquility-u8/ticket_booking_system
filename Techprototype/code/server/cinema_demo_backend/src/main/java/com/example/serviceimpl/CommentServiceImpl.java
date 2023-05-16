package com.example.serviceimpl;

import com.example.dao.CommentDao;
import com.example.entity.CommentEntity;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public CommentEntity save(CommentEntity comment) {
        return commentDao.save(comment);
    }

    @Override
    public void deleteById(int id) {
        commentDao.deleteById(id);
    }

    @Override
    public Optional<CommentEntity> findById(int id) {
        return commentDao.findById(id);
    }

    @Override
    public List<CommentEntity> findByMovieId(int movieId) {
        return commentDao.findByMovieId(movieId);
    }

    @Override
    public List<CommentEntity> findByUserId(int userId) {
        return commentDao.findByUserId(userId);
    }

    @Override
    public List<CommentEntity> findAll() {
        return commentDao.findAll();
    }
}
