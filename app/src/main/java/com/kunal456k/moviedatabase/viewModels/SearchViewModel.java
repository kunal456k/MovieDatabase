package com.kunal456k.moviedatabase.viewModels;

import androidx.lifecycle.LiveData;

import com.kunal456k.moviedatabase.components.ActivityScope;
import com.kunal456k.moviedatabase.models.Movie;
import com.kunal456k.moviedatabase.repository.MovieSearchRepository;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
public class SearchViewModel extends MovieViewModel{

    private final MovieSearchRepository searchRepository;

    @Inject
    public SearchViewModel(MovieSearchRepository searchRepository){
        this.searchRepository = searchRepository;
    }

    public void onSearchTextChanged(CharSequence search){
        searchRepository.performSearch(search);
    }

    public LiveData<List<Movie>> getSearchLiveData(){
        movies = searchRepository.searchLiveData;
        return movies;
    }

    public LiveData<String> getFailedStatus(){
        failedStatus = searchRepository.failedStatus;
        return failedStatus;
    }
}
