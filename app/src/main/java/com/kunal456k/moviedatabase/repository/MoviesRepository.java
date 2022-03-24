package com.kunal456k.moviedatabase.repository;

import com.kunal456k.moviedatabase.components.ActivityScope;
import com.kunal456k.moviedatabase.services.MovieApi;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;

@ActivityScope
public class MoviesRepository {

    private final MovieApi movieApi;

    @Inject
    public MoviesRepository(MovieApi movieApi){
        this.movieApi = movieApi;
    }
}
