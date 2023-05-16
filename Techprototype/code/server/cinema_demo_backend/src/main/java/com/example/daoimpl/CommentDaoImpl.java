package com.example.daoimpl;

import com.example.dao.CommentDao;
import com.example.entity.CommentEntity;
import com.example.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentEntity save(CommentEntity comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteById(int id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Optional<CommentEntity> findById(int id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<CommentEntity> findByMovieId(int movieId) {
        return commentRepository.findByMovieId(movieId);
    }

    @Override
    public List<CommentEntity> findByUserId(int userId) {
        return commentRepository.findByUserId(userId);
    }

    @Override
    public List<CommentEntity> findAll() {
        return commentRepository.findAll();
    }
}