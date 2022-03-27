package com.kunal456k.moviedatabase.db.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.kunal456k.moviedatabase.db.entities.Language;
import com.kunal456k.moviedatabase.db.entities.LanguageMovieCrossRef;
import com.kunal456k.moviedatabase.db.entities.Movie;

import java.util.List;

public class LanguageWithMovies {
    @Embedded public Language language;
    @Relation(parentColumn = "languageName", entityColumn = "movieId",
    associateBy = @Junction(value = LanguageMovieCrossRef.class, parentColumn = "languageName", entityColumn = "movieId"))
    public List<Movie> movies;
}
