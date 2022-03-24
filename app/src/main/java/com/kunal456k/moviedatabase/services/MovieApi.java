package com.kunal456k.moviedatabase.services;

import com.kunal456k.moviedatabase.models.TrendingMovies;

import retrofit2.http.GET;

public interface MovieApi {

    @GET("")
    TrendingMovies getTrendingMovies();

}
