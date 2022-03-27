package com.kunal456k.moviedatabase.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.kunal456k.moviedatabase.db.entities.Language;

@Dao
public interface LanguageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLanguage(Language language);

}
