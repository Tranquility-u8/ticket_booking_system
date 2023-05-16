package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "screening", schema = "cinema_demo_data", catalog = "")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ScreeningEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "cinema_id")
    private int cinemaId;
    @Basic
    @Column(name = "movie_id")
    private int movieId;
    @Basic
    @Column(name = "start_time")
    private Timestamp startTime;

    @ManyToOne(fetch = FetchType.LAZY)// 我们要用这个来接收参数，则不使用这两个插入
    @JoinColumn(name = "cinema_id", insertable = false, updatable = false)
    private CinemaEntity cinema;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    private MovieEntity movie;

    @OneToOne(mappedBy = "screening", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private TicketEntity ticket;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScreeningEntity that = (ScreeningEntity) o;
        return id == that.id && cinemaId == that.cinemaId && movieId == that.movieId && Objects.equals(startTime, that.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cinemaId, movieId, startTime);
    }
}
