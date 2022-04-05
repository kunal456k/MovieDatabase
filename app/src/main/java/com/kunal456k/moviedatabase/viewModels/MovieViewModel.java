package com.kunal456k.moviedatabase.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kunal456k.moviedatabase.db.entities.Movie;

import java.util.List;

public class MovieViewModel extends ViewModel {
    protected LiveData<List<Movie>> movies;
    public LiveData<String> failedStatus;


}
