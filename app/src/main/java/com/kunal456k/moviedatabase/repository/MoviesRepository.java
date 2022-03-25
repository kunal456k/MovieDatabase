package com.kunal456k.moviedatabase.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.kunal456k.moviedatabase.components.ActivityScope;
import com.kunal456k.moviedatabase.models.Movie;
import com.kunal456k.moviedatabase.models.NowPlayingResponse;
import com.kunal456k.moviedatabase.models.TrendingResponse;
import com.kunal456k.moviedatabase.services.MovieApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void getTrending(){
        Observable<TrendingResponse> observable = movieApi.getTrendingMovies();
        Observable<List<Movie>> listObservable = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(TrendingResponse::getMovies);
        listObservable.subscribe(this::onTrendingFetchSuccess, this::onTrendingFetchError);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void getNowPlaying() {
        Observable<NowPlayingResponse> responseObservable = movieApi.getNowPlayingMovies();
        responseObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .map(NowPlayingResponse::getMovies).subscribe(this::onNowPlayingFetchSuccess, this::onNowPlayingFetchError);
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
        if (movies == null){
            onNowPlayingFetchError(null);
            return;
        }
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
        if (movies == null){
            onTrendingFetchError(null);
            return;
        }
        Log.d(TAG, "onTrendingFetchSuccess: ");
        failedTrendingStatus.postValue("");
        trendingMovies.postValue(movies);
    }
}
