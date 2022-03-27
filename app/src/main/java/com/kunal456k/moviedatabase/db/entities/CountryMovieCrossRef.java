package com.kunal456k.moviedatabase.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"countryName", "movieId"})
public class CountryMovieCrossRef {
    @NonNull
    String countryName;
    int movieId;

    public CountryMovieCrossRef(@NonNull String countryName, int movieId){
        this.countryName = countryName;
        this.movieId = movieId;
    }

    @NonNull
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(@NonNull String countryName) {
        this.countryName = countryName;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
