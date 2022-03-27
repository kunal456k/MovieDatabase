package com.kunal456k.moviedatabase.services;

import com.kunal456k.moviedatabase.db.entities.Movie;
import com.kunal456k.moviedatabase.models.MovieApiResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("trending/movie/week")
    Observable<MovieApiResponse> getTrendingMovies();

    @GET("movie/now_playing")
    Observable<MovieApiResponse> getNowPlayingMovies();

    @GET("search/movie")
    Observable<MovieApiResponse> getSearchResponse(@Query("query") String search, @Query("language") String language);

    @GET("movie/{movie_id}")
    Observable<Movie> getMovieDetails(@Path("movie_id") int movieId);
}
