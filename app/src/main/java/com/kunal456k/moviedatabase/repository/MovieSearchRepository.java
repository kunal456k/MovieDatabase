package com.kunal456k.moviedatabase.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.kunal456k.moviedatabase.models.Movie;
import com.kunal456k.moviedatabase.models.SearchResponse;
import com.kunal456k.moviedatabase.services.MovieApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class MovieSearchRepository {
    private static final String TAG = MovieSearchRepository.class.getSimpleName();

    public final MutableLiveData<List<Movie>> searchLiveData = new MutableLiveData<>();
    public MutableLiveData<String> failedStatus = new MutableLiveData<>();
    private final MovieApi movieApi;

    @Inject
    public MovieSearchRepository(MovieApi movieApi){
        this.movieApi = movieApi;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void performSearch(CharSequence search) {
        String searchVal = search.toString().trim();
        if (searchVal.isEmpty()){
            Log.d(TAG, "performSearch: remove search results");
            searchLiveData.postValue(new ArrayList<>());
            return;
        }
        Observable<SearchResponse> responseObservable = movieApi.getSearchResponse(searchVal, "en");
        responseObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .map(SearchResponse::getMovies).subscribe(this::onSearchSuccess, this::onSearchError);
    }

    private void onSearchError(Throwable throwable) {
        if (throwable == null){
            Log.d(TAG, "onSearchError: null");
            failedStatus.postValue("No Results Found");
        }else {
            Log.d(TAG, "onSearchError: "+throwable.getMessage());
            throwable.printStackTrace();
            failedStatus.postValue("Unable to search, Please check internet connection...");
        }
    }

    private void onSearchSuccess(List<Movie> movies) {
        if (movies == null || movies.size() == 0){
            onSearchError(null);
            return;
        }
        failedStatus.postValue("");
        Log.d(TAG, "onSearchSuccess: "+movies.size());
        searchLiveData.postValue(movies);
    }
}
