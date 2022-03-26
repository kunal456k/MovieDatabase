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
    private String originalLanguage;
    @SerializedName("title")
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
    private List<ProductionCompany> productionCompanies;
    @SerializedName("production_countries")
    private List<Country> countries;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("revenue")
    private int revenue;
    @SerializedName("spoken_languages")
    private List<Language> languages;
    @SerializedName("status")
    private String status;
    @SerializedName("vote_average")
    private float rating;
    @SerializedName("vote_count")
    private int voteCount;

    private String commaSeparatedGeneres;
    private String commaSeparatedCompanies;
    private String commaSeparatedCountries;
    private String commaSeparatedLanguages;

    public String getCommaSeparatedGeneres() {
        return commaSeparatedGeneres;
    }

    public void setCommaSeparatedGeneres(String commaSeparatedGeneres) {
        this.commaSeparatedGeneres = commaSeparatedGeneres;
    }

    public String getCommaSeparatedCompanies() {
        return commaSeparatedCompanies;
    }

    public void setCommaSeparatedCompanies(String commaSeparatedCompanies) {
        this.commaSeparatedCompanies = commaSeparatedCompanies;
    }

    public String getCommaSeparatedCountries() {
        return commaSeparatedCountries;
    }

    public void setCommaSeparatedCountries(String commaSeparatedCountries) {
        this.commaSeparatedCountries = commaSeparatedCountries;
    }

    public String getCommaSeparatedLanguages() {
        return commaSeparatedLanguages;
    }

    public void setCommaSeparatedLanguages(String commaSeparatedLanguages) {
        this.commaSeparatedLanguages = commaSeparatedLanguages;
    }

    public int getMovieId() {
        return movieId;
    }

    public boolean isAdultMovie() {
        return adultMovie;
    }

    public List<MovieGenre> getGenres() {
        return genres;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
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

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getRevenue() {
        return revenue;
    }

    public List<Language> getLanguages() {
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
