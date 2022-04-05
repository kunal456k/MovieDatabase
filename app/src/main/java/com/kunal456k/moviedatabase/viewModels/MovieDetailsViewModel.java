package com.kunal456k.moviedatabase.viewModels;

import android.view.View;
import android.widget.CompoundButton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kunal456k.moviedatabase.db.entities.Movie;
import com.kunal456k.moviedatabase.helpers.DeeplinkHelper;
import com.kunal456k.moviedatabase.repository.MoviesRepository;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MovieDetailsViewModel extends ViewModel {

    private final MoviesRepository moviesRepository;

    private final MutableLiveData<String> deepLinkLiveData = new MutableLiveData<>();
    private final CompositeDisposable compositeDisposable;

    private MutableLiveData<Movie> movieDetailsLiveData;
    private LiveData<String> failedStatus;

    @Inject
    public MovieDetailsViewModel(MoviesRepository moviesRepository){
        this.moviesRepository = moviesRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    public void getMovieDetails(int movieId){
        movieDetailsLiveData.postValue(null);
        movieDetailsLiveData = moviesRepository.movieDetailsLiveData;
        compositeDisposable.add(moviesRepository.getMovieDetails(movieId));
    }

    public LiveData<Movie> getMovieDetails(){
        movieDetailsLiveData = moviesRepository.movieDetailsLiveData;
        return movieDetailsLiveData;
    }

    public MutableLiveData<String> getDeepLinkLiveData() {
        return deepLinkLiveData;
    }

    public LiveData<String> getFailedStatus() {
        failedStatus = moviesRepository.failedMovieDetailsStatus;
        return failedStatus;
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        moviesRepository.toggleBookmark(movieDetailsLiveData.getValue(), isChecked);
    }

    public void onShareDeepLinkClick(View v) {
        deepLinkLiveData.setValue("");
        if (movieDetailsLiveData == null) return;
        if (movieDetailsLiveData.getValue() == null) return;
        if (movieDetailsLiveData.getValue().getMovieId() == 0) return;
        String deepLink = DeeplinkHelper.createDeeplinkForMovie(movieDetailsLiveData.getValue().getMovieId());
        deepLinkLiveData.setValue(deepLink);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
