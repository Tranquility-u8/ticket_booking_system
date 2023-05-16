package com.example.service;

import com.example.entity.CommentEntity;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    CommentEntity save(CommentEntity comment);
    void deleteById(int id);
    Optional<CommentEntity> findById(int id);
    List<CommentEntity> findByMovieId(int movieId);
    List<CommentEntity> findByUserId(int userId);
    List<CommentEntity> findAll();
}
