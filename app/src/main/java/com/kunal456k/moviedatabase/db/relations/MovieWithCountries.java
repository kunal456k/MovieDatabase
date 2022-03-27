package com.kunal456k.moviedatabase.db.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.kunal456k.moviedatabase.db.entities.Country;
import com.kunal456k.moviedatabase.db.entities.LanguageMovieCrossRef;
import com.kunal456k.moviedatabase.db.entities.Movie;

import java.util.List;

public class MovieWithCountries {
    @Embedded public Movie movie;
    @Relation(parentColumn = "movieId", entityColumn = "countryName",
            associateBy = @Junction(value = LanguageMovieCrossRef.class, parentColumn = "movieId", entityColumn = "countryName"))
    public List<Country> countries;
}
