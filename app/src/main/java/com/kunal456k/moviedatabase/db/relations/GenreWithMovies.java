package com.kunal456k.moviedatabase.db.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.kunal456k.moviedatabase.db.entities.Genre;
import com.kunal456k.moviedatabase.db.entities.LanguageMovieCrossRef;
import com.kunal456k.moviedatabase.db.entities.Movie;

import java.util.List;

public class GenreWithMovies {
    @Embedded public Genre genre;
    @Relation(parentColumn = "genreId", entityColumn = "movieId",
            associateBy = @Junction(value = LanguageMovieCrossRef.class, parentColumn = "genreId", entityColumn = "movieId"))
    public List<Movie> movies;
}
