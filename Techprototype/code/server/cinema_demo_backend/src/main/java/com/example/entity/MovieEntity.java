package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "movie", schema = "cinema_demo_data", catalog = "")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class MovieEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "release_date")
    private String releaseDate;// 上映时间
    @Basic
    @Column(name = "genre")
    private String genre;// 电影分类
    @Basic
    @Column(name = "director")
    private String director;// 导演
    @Basic
    @Column(name = "cast")
    private String cast;// 演员表
    @Basic
    @Column(name = "description")
    private String description;// 电影描述
    @Basic
    @Column(name = "poster_url")
    private String posterUrl;// 海报URL链接
    @Basic
    @Column(name = "movie_url")
    private String movieUrl;// 电影URL链接
    @Basic
    @Column(name = "create_date", insertable = false)
    private Timestamp createDate;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ScreeningEntity> screenings;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CommentEntity> comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getMovieUrl() {
        return movieUrl;
    }

    public void setMovieUrl(String movieUrl) {
        this.movieUrl = movieUrl;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieEntity that = (MovieEntity) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(releaseDate, that.releaseDate) && Objects.equals(genre, that.genre) && Objects.equals(director, that.director) && Objects.equals(cast, that.cast) && Objects.equals(description, that.description) && Objects.equals(posterUrl, that.posterUrl) && Objects.equals(movieUrl, that.movieUrl) && Objects.equals(createDate, that.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, releaseDate, genre, director, cast, description, posterUrl, movieUrl, createDate);
    }
}
