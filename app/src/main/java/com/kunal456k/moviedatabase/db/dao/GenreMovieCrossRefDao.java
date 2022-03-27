package com.kunal456k.moviedatabase.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.kunal456k.moviedatabase.db.entities.GenreMovieCrossRef;

@Dao
public interface GenreMovieCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGenreMovieCrossRef(GenreMovieCrossRef genreMovieCrossRef);

}
