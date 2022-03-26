package com.kunal456k.moviedatabase.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TrendingResponse implements Serializable {

    @SerializedName("page")
    private int pageNumber;
    @SerializedName("results")
    private List<MovieDetails> movies;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<MovieDetails> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieDetails> movies) {
        this.movies = movies;
    }
}
