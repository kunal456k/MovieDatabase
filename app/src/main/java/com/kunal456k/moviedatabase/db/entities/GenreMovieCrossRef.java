package com.kunal456k.moviedatabase.db.entities;

import androidx.room.Entity;

@Entity(primaryKeys = {"genreId", "movieId"})
public class GenreMovieCrossRef {
    int genreId;
    int movieId;

    public GenreMovieCrossRef(int genreId, int movieId) {
        this.genreId = genreId;
        this.movieId = movieId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
