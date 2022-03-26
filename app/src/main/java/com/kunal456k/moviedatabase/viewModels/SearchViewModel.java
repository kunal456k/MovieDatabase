package com.kunal456k.moviedatabase.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kunal456k.moviedatabase.components.ActivityScope;
import com.kunal456k.moviedatabase.models.Movie;
import com.kunal456k.moviedatabase.repository.MovieSearchRepository;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
public class SearchViewModel extends MovieViewModel {

    private final MutableLiveData<String> searchInputData = new MutableLiveData<>();
    private final MovieSearchRepository searchRepository;

    @Inject
    public SearchViewModel(MovieSearchRepository searchRepository){
        this.searchRepository = searchRepository;
    }

    public void onSearchTextChanged(CharSequence search){
        String searchVal = search.toString().trim();
        searchInputData.setValue(searchVal);
        searchRepository.performSearch(searchVal);
    }

    public LiveData<List<Movie>> getMoviesLiveData(){
        movies = searchRepository.searchLiveData;
        return movies;
    }

    public MutableLiveData<String> getSearchInputData() {
        return searchInputData;
    }

    public LiveData<String> getFailedStatus(){
        failedStatus = searchRepository.failedStatus;
        return failedStatus;
    }
}
