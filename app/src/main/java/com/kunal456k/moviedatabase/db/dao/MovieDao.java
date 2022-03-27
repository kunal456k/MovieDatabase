package com.kunal456k.moviedatabase.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.kunal456k.moviedatabase.db.entities.Movie;
import com.kunal456k.moviedatabase.db.relations.MovieAndBookmark;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(Movie movie);

    @Transaction
    @Query("SELECT * FROM movie")
    List<Movie> getAllMovies();

    @Transaction
    @Query("SELECT * FROM movie WHERE movieId = :movieId LIMIT 1")
    MovieAndBookmark getMovieAndBookmarkWithMovie(int movieId);

    @Transaction
    @Query("SELECT * FROM movie WHERE movieId = :movieId")
    Single<Movie> getMovieWithId(int movieId);
}
