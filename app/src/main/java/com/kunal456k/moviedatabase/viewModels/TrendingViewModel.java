package com.kunal456k.moviedatabase.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kunal456k.moviedatabase.models.Movie;
import com.kunal456k.moviedatabase.repository.MoviesRepository;

import java.util.List;

import javax.inject.Inject;

public class TrendingViewModel extends ViewModel {

    private final MoviesRepository moviesRepository;

    private LiveData<List<Movie>> nowPlayingMovies;
    private LiveData<List<Movie>> trendingMovies;
    private LiveData<String> failedTrendingStatus;
    private LiveData<String> failedNowPlayingStatus;

    @Inject
    public TrendingViewModel(MoviesRepository moviesRepository){
        this.moviesRepository = moviesRepository;
    }

    public LiveData<List<Movie>> getTrendingMovies() {
        trendingMovies = moviesRepository.trendingMovies;
        moviesRepository.getTrending();
        return trendingMovies;
    }

    public LiveData<String> getFailedTrendingStatus() {
        failedTrendingStatus = moviesRepository.failedTrendingStatus;
        return failedTrendingStatus;
    }

    public LiveData<String> getFailedNowPlayingStatus() {
        failedNowPlayingStatus = moviesRepository.failedNowPlayingStatus;
        return failedNowPlayingStatus;
    }

    public LiveData<List<Movie>> getNowPlayingMovies(){
        nowPlayingMovies = moviesRepository.nowPlayingMovies;
        moviesRepository.getNowPlaying();
        return nowPlayingMovies;
    }
}
