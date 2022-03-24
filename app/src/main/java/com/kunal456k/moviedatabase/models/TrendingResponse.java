package com.kunal456k.moviedatabase.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TrendingResponse implements Serializable {

    @SerializedName("page")
    private int pageNumber;
    @SerializedName("results")
    private List<Movie> movies;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
