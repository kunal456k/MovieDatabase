package com.kunal456k.moviedatabase.db.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.kunal456k.moviedatabase.db.entities.Company;
import com.kunal456k.moviedatabase.db.entities.LanguageMovieCrossRef;
import com.kunal456k.moviedatabase.db.entities.Movie;

import java.util.List;

public class MovieWithCompanies {
    @Embedded public Movie movie;
    @Relation(parentColumn = "movieId", entityColumn = "companyId",
            associateBy = @Junction(value = LanguageMovieCrossRef.class, parentColumn = "movieId", entityColumn = "companyId"))
    public List<Company> companies;
}
