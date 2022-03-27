package com.kunal456k.moviedatabase.db.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.kunal456k.moviedatabase.db.entities.Bookmark;
import com.kunal456k.moviedatabase.db.entities.Movie;

public class MovieAndBookmark {

    @Embedded public Movie movie;
    @Relation(parentColumn = "movieId", entityColumn = "movieId")
     public Bookmark bookmark;
}
