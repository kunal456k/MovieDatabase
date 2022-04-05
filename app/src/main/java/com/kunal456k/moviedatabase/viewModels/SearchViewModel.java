package com.kunal456k.moviedatabase.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kunal456k.moviedatabase.components.ActivityScope;
import com.kunal456k.moviedatabase.db.entities.Movie;
import com.kunal456k.moviedatabase.repository.MoviesRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@ActivityScope
public class SearchViewModel extends MovieViewModel {

    private final MutableLiveData<String> searchInputData = new MutableLiveData<>();
    private final MoviesRepository movieSearchRepository;
    private final CompositeDisposable compositeDisposable;

    @Inject
    public SearchViewModel(MoviesRepository movieSearchRepository){
        this.movieSearchRepository = movieSearchRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    public void onSearchTextChanged(CharSequence search){
        String searchVal = search.toString().trim();
        searchInputData.setValue(searchVal);
        Disposable disposable = movieSearchRepository.performSearch(searchVal);
        if (disposable != null) compositeDisposable.add(disposable);
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

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
