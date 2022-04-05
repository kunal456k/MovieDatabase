package com.kunal456k.moviedatabase.viewModels;

import androidx.lifecycle.LiveData;

import com.kunal456k.moviedatabase.db.entities.Movie;
import com.kunal456k.moviedatabase.repository.MoviesRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class NowPlayingViewModel extends MovieViewModel{

    private final MoviesRepository moviesRepository;
    private final CompositeDisposable compositeDisposable;

    @Inject
    public NowPlayingViewModel(MoviesRepository moviesRepository){
        this.moviesRepository = moviesRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    public LiveData<String> getFailedNowPlayingStatus() {
        failedStatus = moviesRepository.failedNowPlayingStatus;
        return failedStatus;
    }

    public LiveData<List<Movie>> getNowPlayingMovies(){
        movies = moviesRepository.nowPlayingMovies;
        compositeDisposable.add(moviesRepository.getNowPlaying());
        return movies;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
