package com.kunal456k.moviedatabase.db.entities;

import androidx.room.Entity;

@Entity(primaryKeys = {"companyId", "movieId"})
public class CompanyMovieCrossRef {
    private int companyId;
    private int movieId;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
