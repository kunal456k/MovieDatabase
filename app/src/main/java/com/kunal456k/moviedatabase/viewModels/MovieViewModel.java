package com.kunal456k.moviedatabase.viewModels;

import androidx.lifecycle.LiveData;

import com.kunal456k.moviedatabase.models.MovieDetails;

import java.util.List;

public class MovieViewModel {
    protected LiveData<List<MovieDetails>> movies;
    public LiveData<String> failedStatus;
}
