package com.kunal456k.moviedatabase.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kunal456k.moviedatabase.models.MovieDetails;
import com.kunal456k.moviedatabase.repository.MoviesRepository;

import javax.inject.Inject;

public class MovieDetailsViewModel {

    private final MoviesRepository moviesRepository;

    private MutableLiveData<MovieDetails> movieDetailsLiveData;
    private LiveData<String> failedStatus;

    @Inject
    public MovieDetailsViewModel(MoviesRepository moviesRepository){
        this.moviesRepository = moviesRepository;
    }

    public LiveData<MovieDetails> getMovieDetails(int movieId){
        movieDetailsLiveData = moviesRepository.movieDetailsLiveData;
        moviesRepository.getMovieDetails(movieId);
        return movieDetailsLiveData;
    }

    public LiveData<MovieDetails> getMovieDetails(){
        movieDetailsLiveData = moviesRepository.movieDetailsLiveData;
        return movieDetailsLiveData;
    }

    public LiveData<String> getFailedStatus() {
        failedStatus = moviesRepository.failedMovieDetailsStatus;
        return failedStatus;
    }

    public void clearMovieDetails() {
        if (movieDetailsLiveData != null){
            movieDetailsLiveData.postValue(null);
        }
    }
}
