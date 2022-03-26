package com.kunal456k.moviedatabase.services;

import com.kunal456k.moviedatabase.models.NowPlayingResponse;
import com.kunal456k.moviedatabase.models.SearchResponse;
import com.kunal456k.moviedatabase.models.TrendingResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("trending/movie/week")
    Observable<TrendingResponse> getTrendingMovies();

    @GET("movie/now_playing")
    Observable<NowPlayingResponse> getNowPlayingMovies();

    @GET("search/movie")
    Observable<SearchResponse> getSearchResponse(@Query("query") String search, @Query("language") String language);
}
