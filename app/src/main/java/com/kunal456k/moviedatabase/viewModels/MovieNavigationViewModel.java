package com.kunal456k.moviedatabase.viewModels;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kunal456k.moviedatabase.R;
import com.kunal456k.moviedatabase.components.ActivityScope;

import javax.inject.Inject;

@ActivityScope
public class MovieNavigationViewModel extends ViewModel {
    private static final String TAG = MovieNavigationViewModel.class.getSimpleName();

    private final MutableLiveData<Integer> movieIdData = new MutableLiveData<>();
    public final OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            if (movieIdData.getValue() != null && movieIdData.getValue() != 0){
                gotoHome();
            }
        }
    };

    @Inject
    public MovieNavigationViewModel(){}

    public void setMovieId(int movieId){
        movieIdData.postValue(movieId);
    }

    public MutableLiveData<Integer> getMovieIdData() {
        return movieIdData;
    }

    public void gotoHome() {
        movieIdData.postValue(0);
    }
}
