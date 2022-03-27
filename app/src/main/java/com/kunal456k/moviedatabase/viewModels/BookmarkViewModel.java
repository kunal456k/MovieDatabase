package com.kunal456k.moviedatabase.viewModels;

import androidx.lifecycle.LiveData;

import com.kunal456k.moviedatabase.db.entities.Movie;
import com.kunal456k.moviedatabase.repository.MoviesRepository;

import java.util.List;

import javax.inject.Inject;

public class BookmarkViewModel extends MovieViewModel {

    private final MoviesRepository moviesRepository;

    @Inject
    public BookmarkViewModel(MoviesRepository moviesRepository){
        this.moviesRepository = moviesRepository;
    }


    public LiveData<List<Movie>> getBookmarkedMovies(){
        movies = moviesRepository.bookmarkedMovies;
        moviesRepository.getBookmarkedMovies();
        return movies;
    }
}
