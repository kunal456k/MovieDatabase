package com.kunal456k.moviedatabase.viewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kunal456k.moviedatabase.models.Movie;
import com.kunal456k.moviedatabase.repository.MoviesRepository;

import java.util.List;

import javax.inject.Inject;

public class TrendingViewModel extends MovieViewModel {

    private final MoviesRepository moviesRepository;

    @Inject
    public TrendingViewModel(MoviesRepository moviesRepository){
        this.moviesRepository = moviesRepository;
    }

    public LiveData<List<Movie>> getTrendingMovies() {
        movies = moviesRepository.trendingMovies;
        moviesRepository.getTrending();
        return movies;
    }

    public LiveData<String> getFailedTrendingStatus() {
        failedStatus = moviesRepository.failedTrendingStatus;
        return failedStatus;
    }
}
