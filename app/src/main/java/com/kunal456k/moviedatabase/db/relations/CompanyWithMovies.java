package com.kunal456k.moviedatabase.db.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.kunal456k.moviedatabase.db.entities.Company;
import com.kunal456k.moviedatabase.db.entities.LanguageMovieCrossRef;
import com.kunal456k.moviedatabase.db.entities.Movie;

import java.util.List;

public class CompanyWithMovies {
    @Embedded public Company company;
    @Relation(parentColumn = "companyId", entityColumn = "movieId",
            associateBy = @Junction(value = LanguageMovieCrossRef.class, parentColumn = "companyId", entityColumn = "movieId"))
    public List<Movie> movies;
}
