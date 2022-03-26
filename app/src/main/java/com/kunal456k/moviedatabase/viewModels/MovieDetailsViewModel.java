package com.kunal456k.moviedatabase.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kunal456k.moviedatabase.models.MovieDetails;
import com.kunal456k.moviedatabase.repository.MoviesRepository;

import javax.inject.Inject;

public class MovieDetailsViewModel {

    private final MoviesRepository moviesRepository;

    private MutableLiveData<MovieDetails> movieDetailsLiveData;

    @Inject
    public MovieDetailsViewModel(MoviesRepository moviesRepository){
        this.moviesRepository = moviesRepository;
    }

    public LiveData<MovieDetails> getMovieDetails(int movieId){
        movieDetailsLiveData = moviesRepository.movieDetailsLiveData;
        moviesRepository.getMovieDetails(movieId);
        return movieDetailsLiveData;
    }

    public void clearMovieDetails() {
        if (movieDetailsLiveData != null){
            movieDetailsLiveData.postValue(null);
        }
    }
}
