package com.kunal456k.moviedatabase.viewModels;

import androidx.lifecycle.LiveData;

import com.kunal456k.moviedatabase.db.entities.Movie;
import com.kunal456k.moviedatabase.repository.MoviesRepository;

import java.util.List;

import javax.inject.Inject;

public class NowPlayingViewModel extends MovieViewModel{

    private final MoviesRepository moviesRepository;

    @Inject
    public NowPlayingViewModel(MoviesRepository moviesRepository){
        this.moviesRepository = moviesRepository;
    }

    public LiveData<String> getFailedNowPlayingStatus() {
        failedStatus = moviesRepository.failedNowPlayingStatus;
        return failedStatus;
    }

    public LiveData<List<Movie>> getNowPlayingMovies(){
        movies = moviesRepository.nowPlayingMovies;
        moviesRepository.getNowPlaying();
        return movies;
    }
}
