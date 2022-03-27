package com.kunal456k.moviedatabase.db.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Movie {
    @PrimaryKey
    @SerializedName("id")
    private int movieId;
    @SerializedName("adult")
    private boolean adultMovie;
    @Ignore
    @SerializedName("genres")
    private List<Genre> genres;
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
    @Ignore
    @SerializedName("production_companies")
    private List<Company> productionCompanies;
    @Ignore
    @SerializedName("production_countries")
    private List<Country> countries;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("revenue")
    private int revenue;
    @Ignore
    @SerializedName("spoken_languages")
    private List<Language> languages;
    @SerializedName("status")
    private String status;
    @SerializedName("vote_average")
    private float rating;
    @SerializedName("vote_count")
    private int voteCount;

    @Ignore
    private String commaSeparatedGeneres;
    @Ignore
    private String commaSeparatedCompanies;
    @Ignore
    private String commaSeparatedCountries;
    @Ignore
    private String commaSeparatedLanguages;

    public Movie() {
    }

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

    public List<Genre> getGenres() {
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

    public List<Company> getProductionCompanies() {
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

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setAdultMovie(boolean adultMovie) {
        this.adultMovie = adultMovie;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setProductionCompanies(List<Company> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}
