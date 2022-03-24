package com.kunal456k.moviedatabase.services;

import com.kunal456k.moviedatabase.models.NowPlayingResponse;
import com.kunal456k.moviedatabase.models.TrendingResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {

    @GET("trending/movie/week")
    Call<TrendingResponse> getTrendingMovies();

    @GET("movie/now_playing")
    Call<NowPlayingResponse> getNowPlayingMovies();

}
