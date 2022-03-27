package com.kunal456k.moviedatabase.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.kunal456k.moviedatabase.db.entities.CompanyMovieCrossRef;

@Dao
public interface CompanyMovieCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCompanyMovieCrossRef(CompanyMovieCrossRef companyMovieCrossRef);

}
