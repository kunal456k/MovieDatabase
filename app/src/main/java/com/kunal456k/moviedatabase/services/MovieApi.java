package com.kunal456k.moviedatabase.services;

import com.kunal456k.moviedatabase.models.NowPlayingResponse;
import com.kunal456k.moviedatabase.models.TrendingResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MovieApi {

    @GET("trending/movie/week")
    Observable<TrendingResponse> getTrendingMovies();

    @GET("movie/now_playing")
    Observable<NowPlayingResponse> getNowPlayingMovies();

}
