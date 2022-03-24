package com.kunal456k.moviedatabase.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.kunal456k.moviedatabase.components.ActivityScope;
import com.kunal456k.moviedatabase.models.Movie;
import com.kunal456k.moviedatabase.models.NowPlayingResponse;
import com.kunal456k.moviedatabase.models.TrendingResponse;
import com.kunal456k.moviedatabase.services.MovieApi;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@ActivityScope
public class MoviesRepository {
    private static final String TAG = MoviesRepository.class.getSimpleName();

    private final MovieApi movieApi;

    public MutableLiveData<List<Movie>> trendingMovies = new MutableLiveData<>();
    public MutableLiveData<List<Movie>> nowPlayingMovies = new MutableLiveData<>();
    public MutableLiveData<String> failedTrendingStatus = new MutableLiveData<>();
    public MutableLiveData<String> failedNowPlayingStatus = new MutableLiveData<>();

    @Inject
    public MoviesRepository(MovieApi movieApi){
        this.movieApi = movieApi;
    }

    public void getTrending(){
        movieApi.getTrendingMovies().enqueue(new Callback<TrendingResponse>() {
            @Override
            public void onResponse(@NonNull Call<TrendingResponse> call, @NonNull Response<TrendingResponse> response) {
                if (response.body() != null && response.body().getMovies().size() != 0){
                    onTrendingFetchSuccess(response.body().getMovies());
                }else {
                    onTrendingFetchError(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TrendingResponse> call, @NonNull Throwable t) {
                onTrendingFetchError(t);
            }
        });
    }

    public void getNowPlaying() {
        movieApi.getNowPlayingMovies().enqueue(new Callback<NowPlayingResponse>() {
            @Override
            public void onResponse(@NonNull Call<NowPlayingResponse> call, @NonNull Response<NowPlayingResponse> response) {
                if (response.body() != null && response.body().getMovies().size() != 0){
                    onNowPlayingFetchSuccess(response.body().getMovies());
                }else {
                    onNowPlayingFetchError(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<NowPlayingResponse> call, @NonNull Throwable t) {
                onNowPlayingFetchError(t);
            }
        });
    }

    private void onNowPlayingFetchError(Throwable t) {
        if (t == null){
            Log.d(TAG, "onNowPlayingFetchError: null response");
            failedNowPlayingStatus.postValue("No Results Found");
        }else {
            Log.d(TAG, "onNowPlayingFetchError: error "+t.getMessage());
            t.printStackTrace();
            failedNowPlayingStatus.postValue("Error Connecting, Please check internet and retry");
        }
    }

    private void onNowPlayingFetchSuccess(List<Movie> movies) {
        Log.d(TAG, "onNowPlayingFetchSuccess: ");
        failedNowPlayingStatus.postValue("");
        nowPlayingMovies.postValue(movies);
    }

    private void onTrendingFetchError(Throwable t) {
        if (t == null){
            Log.d(TAG, "onTrendingFetchError: null response");
            failedTrendingStatus.postValue("No Results Found");
        }else {
            Log.d(TAG, "onTrendingFetchError: error "+t.getMessage());
            t.printStackTrace();
            failedTrendingStatus.postValue("Error Connecting, Please check internet and retry");
        }
    }

    private void onTrendingFetchSuccess(List<Movie> movies) {
        Log.d(TAG, "onTrendingFetchSuccess: ");
        failedTrendingStatus.postValue("");
        trendingMovies.postValue(movies);
    }
}
