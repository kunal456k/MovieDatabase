package com.kunal456k.moviedatabase.db.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.kunal456k.moviedatabase.db.entities.Country;
import com.kunal456k.moviedatabase.db.entities.LanguageMovieCrossRef;
import com.kunal456k.moviedatabase.db.entities.Movie;

import java.util.List;

public class CountryWithMovies {
    @Embedded public Country country;
    @Relation(parentColumn = "countryName", entityColumn = "movieId",
            associateBy = @Junction(value = LanguageMovieCrossRef.class, parentColumn = "countryName", entityColumn = "movieId"))
    public List<Movie> movies;
}
