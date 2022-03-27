package com.kunal456k.moviedatabase.viewModels;

import android.widget.CompoundButton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kunal456k.moviedatabase.db.entities.Movie;
import com.kunal456k.moviedatabase.repository.MoviesRepository;

import javax.inject.Inject;

public class MovieDetailsViewModel extends ViewModel{

    private final MoviesRepository moviesRepository;

    private MutableLiveData<Movie> movieDetailsLiveData;
    private LiveData<String> failedStatus;

    @Inject
    public MovieDetailsViewModel(MoviesRepository moviesRepository){
        this.moviesRepository = moviesRepository;
    }

    public void getMovieDetails(int movieId){
        movieDetailsLiveData.postValue(null);
        movieDetailsLiveData = moviesRepository.movieDetailsLiveData;
        moviesRepository.getMovieDetails(movieId);
    }

    public LiveData<Movie> getMovieDetails(){
        movieDetailsLiveData = moviesRepository.movieDetailsLiveData;
        return movieDetailsLiveData;
    }

    public LiveData<String> getFailedStatus() {
        failedStatus = moviesRepository.failedMovieDetailsStatus;
        return failedStatus;
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        moviesRepository.toggleBookmark(movieDetailsLiveData.getValue(), isChecked);
    }
}
