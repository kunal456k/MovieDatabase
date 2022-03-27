package com.kunal456k.moviedatabase.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.kunal456k.moviedatabase.db.entities.CountryMovieCrossRef;

@Dao
public interface CountryMovieCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCountryMovieCrossRef(CountryMovieCrossRef countryMovieCrossRef);
}
