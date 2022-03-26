package com.kunal456k.moviedatabase.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetails {
    @SerializedName("id")
    private int movieId;
    @SerializedName("adult")
    private boolean adultMovie;
    @SerializedName("genres")
    private List<MovieGenre> genres;
    @SerializedName("original_language")
    private String language;
    @SerializedName("original_title")
    private String title;
    @SerializedName("overview")
    private String overview;
    @SerializedName("popularity")
    private double popularity;
    @SerializedName("poster_path")
    private String posterUrl;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("production_companies")
    private List<ProductionCompanies> productionCompanies;
    @SerializedName("production_countries")
    private List<Countries> countries;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("revenue")
    private int revenue;
    @SerializedName("spoken_languages")
    private List<Languages> languages;
    @SerializedName("status")
    private String status;
    @SerializedName("vote_average")
    private float rating;
    @SerializedName("vote_count")
    private int voteCount;

    public int getMovieId() {
        return movieId;
    }

    public boolean isAdultMovie() {
        return adultMovie;
    }

    public List<MovieGenre> getGenres() {
        return genres;
    }

    public String getLanguage() {
        return language;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public List<ProductionCompanies> getProductionCompanies() {
        return productionCompanies;
    }

    public List<Countries> getCountries() {
        return countries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getRevenue() {
        return revenue;
    }

    public List<Languages> getLanguages() {
        return languages;
    }

    public String getStatus() {
        return status;
    }

    public float getRating() {
        return rating;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public String getBackdropPath() {
        return backdropPath;
    }
}
