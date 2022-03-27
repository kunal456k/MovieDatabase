package com.kunal456k.moviedatabase.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.kunal456k.moviedatabase.db.entities.Bookmark;
import com.kunal456k.moviedatabase.db.entities.Movie;
import com.kunal456k.moviedatabase.db.relations.MovieAndBookmark;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface BookmarkDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBookmark(Bookmark bookmark);

    @Transaction
    @Query("SELECT * FROM bookmark")
    List<Bookmark> getAllBookmarks();

    @Transaction
    @Query("SELECT movieId FROM bookmark WHERE bookmarkId = :bookmarkId LIMIT 1")
    int getMovieIdForBookmarkId(int bookmarkId);

    @Transaction
    @Query("SELECT * FROM movie WHERE movie.movieId IN (SELECT movieId FROM bookmark)")
    Single<List<MovieAndBookmark>> getAllBookmarkedMovieIds();

    @Delete
    void deleteBookmark(Bookmark bookmark);

    @Query("DELETE FROM bookmark WHERE bookmarkId = :bookmarkId")
    void deleteBookmarkWithId(int bookmarkId);
}
