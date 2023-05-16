package com.example.repository;

import com.example.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findByMovieId(int movieId);
    List<CommentEntity> findByUserId(int userId);
}
