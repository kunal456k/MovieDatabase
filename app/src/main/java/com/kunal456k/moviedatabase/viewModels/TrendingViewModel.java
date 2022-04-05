package com.kunal456k.moviedatabase.viewModels;

import androidx.lifecycle.LiveData;

import com.kunal456k.moviedatabase.db.entities.Movie;
import com.kunal456k.moviedatabase.repository.MoviesRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class TrendingViewModel extends MovieViewModel {

    private final MoviesRepository moviesRepository;
    private final CompositeDisposable container;

    @Inject
    public TrendingViewModel(MoviesRepository moviesRepository){
        this.moviesRepository = moviesRepository;
        this.container = new CompositeDisposable();
    }

    public LiveData<List<Movie>> getTrendingMovies() {
        movies = moviesRepository.trendingMovies;
        container.add(moviesRepository.getTrending());
        return movies;
    }

    public LiveData<String> getFailedTrendingStatus() {
        failedStatus = moviesRepository.failedTrendingStatus;
        return failedStatus;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        container.dispose();
    }
}
