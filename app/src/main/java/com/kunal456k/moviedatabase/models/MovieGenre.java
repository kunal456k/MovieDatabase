package com.kunal456k.moviedatabase.models;

import com.google.gson.annotations.SerializedName;

public class MovieGenre {
    @SerializedName("id")
    private int genreId;
    @SerializedName("name")
    private String genreName;

    public int getGenreId() {
        return genreId;
    }

    public String getGenreName() {
        return genreName;
    }
}
