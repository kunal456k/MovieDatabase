package com.kunal456k.moviedatabase.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.kunal456k.moviedatabase.db.entities.LanguageMovieCrossRef;

@Dao
public interface LanguageMovieCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLanguageMovieCrossRef(LanguageMovieCrossRef languageMovieCrossRef);

}
