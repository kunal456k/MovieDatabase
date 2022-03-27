package com.kunal456k.moviedatabase.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"languageName", "movieId"})
public class LanguageMovieCrossRef {
    @NonNull
    String languageName;
    int movieId;

    public LanguageMovieCrossRef(@NonNull String languageName, int movieId) {
        this.languageName = languageName;
        this.movieId = movieId;
    }

    @NonNull
    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(@NonNull String languageName) {
        this.languageName = languageName;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
