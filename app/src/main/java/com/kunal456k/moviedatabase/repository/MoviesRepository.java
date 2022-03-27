package com.kunal456k.moviedatabase.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.kunal456k.moviedatabase.components.ActivityScope;
import com.kunal456k.moviedatabase.db.dao.MovieDao;
import com.kunal456k.moviedatabase.db.entities.Movie;
import com.kunal456k.moviedatabase.models.NowPlayingResponse;
import com.kunal456k.moviedatabase.models.SearchResponse;
import com.kunal456k.moviedatabase.models.TrendingResponse;
import com.kunal456k.moviedatabase.services.MovieApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@ActivityScope
public class MoviesRepository {
    private static final String TAG = MoviesRepository.class.getSimpleName();

    private final MovieApi movieApi;
    private final MovieDao movieDao;
    public final MutableLiveData<List<Movie>> trendingMovies = new MutableLiveData<>();
    public final MutableLiveData<List<Movie>> nowPlayingMovies = new MutableLiveData<>();
    public final MutableLiveData<String> failedTrendingStatus = new MutableLiveData<>();
    public final MutableLiveData<String> failedNowPlayingStatus = new MutableLiveData<>();
    public final MutableLiveData<Movie> movieDetailsLiveData = new MutableLiveData<>();
    public final MutableLiveData<List<Movie>> movieSearchLiveData = new MutableLiveData<>();
    public final MutableLiveData<String> failedSearchStatus = new MutableLiveData<>();
    public final MutableLiveData<String> failedMovieDetailsStatus = new MutableLiveData<>();

    @Inject
    public MoviesRepository(MovieApi movieApi, MovieDao movieDao){
        this.movieApi = movieApi;
        this.movieDao = movieDao;
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

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void getMovieDetails(int movieId) {
        failedMovieDetailsStatus.postValue("");
        Observable<Movie> responseObservable = movieApi.getMovieDetails(movieId);
        responseObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onMovieDetailsSuccess, this::onMovieDetailsError);
    }

    private void onMovieDetailsError(Throwable throwable) {
        Log.d(TAG, "onMovieDetailsError: unable to fetch movie details");
        movieDetailsLiveData.postValue(null);
        failedMovieDetailsStatus.postValue("Unable to load movie details, Please check internet connection");
    }

    private void onMovieDetailsSuccess(Movie movie) {
        if (movie == null){
            onMovieDetailsError(null);
            return;
        }
        failedMovieDetailsStatus.postValue("");
        movieDetailsLiveData.postValue(movie);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void performSearch(String search) {
        if (search.isEmpty()){
            Log.d(TAG, "performSearch: remove search results");
            movieSearchLiveData.postValue(new ArrayList<>());
            return;
        }
        Observable<SearchResponse> responseObservable = movieApi.getSearchResponse(search, "en");
        responseObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .map(SearchResponse::getMovies).subscribe(this::onSearchSuccess, this::onSearchError);
    }

    private void onSearchError(Throwable throwable) {
        if (throwable == null){
            Log.d(TAG, "onSearchError: null");
            failedSearchStatus.postValue("No Results Found");
        }else {
            Log.d(TAG, "onSearchError: "+throwable.getMessage());
            throwable.printStackTrace();
            failedSearchStatus.postValue("Unable to search, Please check internet connection...");
        }
        movieSearchLiveData.postValue(new ArrayList<>());
    }

    private void onSearchSuccess(List<Movie> movies) {
        if (movies == null || movies.size() == 0){
            onSearchError(null);
            return;
        }
        failedSearchStatus.postValue("");
        Log.d(TAG, "onSearchSuccess: "+movies.size());
        movieSearchLiveData.postValue(movies);
    }
}
