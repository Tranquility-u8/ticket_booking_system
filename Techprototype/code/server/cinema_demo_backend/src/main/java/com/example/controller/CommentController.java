package com.example.controller;

import com.example.entity.CommentEntity;
import com.example.entity.MovieEntity;
import com.example.entity.UserEntity;
import com.example.service.CommentService;
import com.example.service.MovieService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private UserService userService;

    @RequestMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute CommentEntity formData,
                                    @RequestBody(required = false) CommentEntity jsonData) {
        CommentEntity comment = (jsonData != null) ? jsonData : formData;
        int movieId = comment.getMovieId();
        int userId = comment.getUserId();
        Optional<MovieEntity> movie = movieService.findById(movieId);
        UserEntity user = userService.findUserById(userId);
        if (!movie.isPresent() || user == null) {
            // 返回错误信息给用户
            return ResponseEntity.badRequest().body("Invalid movieId or userId");
        } else {
            // 保存评论信息
            CommentEntity savedComment = commentService.save(comment);
            return ResponseEntity.ok(savedComment);
        }
    }

    @RequestMapping("/delete")
    public void deleteById(@RequestParam int id) {
        commentService.deleteById(id);
    }

    @RequestMapping("/getById")
    public ResponseEntity<CommentEntity> getById(@RequestParam int id) {
        Optional<CommentEntity> comment = commentService.findById(id);
        if (comment.isPresent()) {
            return ResponseEntity.ok(comment.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/search")
    public List<CommentEntity> search(@RequestParam(required = false) Integer movieId,
                                      @RequestParam(required = false) Integer userId) {
        if (movieId != null && userId != null) {
            // 根据 movieId 和 userId 查询评论信息
            // ...
        } else if (movieId != null) {
            // 根据 movieId 查询评论信息
            return commentService.findByMovieId(movieId);
        } else if (userId != null) {
            // 根据 userId 查询评论信息
            return commentService.findByUserId(userId);
        } else {
            return null;
        }

        return null;
    }
}