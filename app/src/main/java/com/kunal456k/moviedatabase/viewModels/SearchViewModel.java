package com.kunal456k.moviedatabase.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kunal456k.moviedatabase.components.ActivityScope;
import com.kunal456k.moviedatabase.db.entities.Movie;
import com.kunal456k.moviedatabase.repository.MoviesRepository;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
public class SearchViewModel extends MovieViewModel {

    private final MutableLiveData<String> searchInputData = new MutableLiveData<>();
    private final MoviesRepository movieSearchRepository;

    @Inject
    public SearchViewModel(MoviesRepository movieSearchRepository){
        this.movieSearchRepository = movieSearchRepository;
    }

    public void onSearchTextChanged(CharSequence search){
        String searchVal = search.toString().trim();
        searchInputData.setValue(searchVal);
        movieSearchRepository.performSearch(searchVal);
    }

    public LiveData<List<Movie>> getMoviesLiveData(){
        movies = movieSearchRepository.movieSearchLiveData;
        return movies;
    }

    public MutableLiveData<String> getSearchInputData() {
        return searchInputData;
    }

    public LiveData<String> getFailedStatus(){
        failedStatus = movieSearchRepository.failedSearchStatus;
        return failedStatus;
    }
}
