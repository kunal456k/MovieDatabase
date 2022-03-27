package com.kunal456k.moviedatabase.viewModels;

import androidx.lifecycle.LiveData;

import com.kunal456k.moviedatabase.db.entities.Movie;

import java.util.List;

public class MovieViewModel {
    protected LiveData<List<Movie>> movies;
    public LiveData<String> failedStatus;
}
