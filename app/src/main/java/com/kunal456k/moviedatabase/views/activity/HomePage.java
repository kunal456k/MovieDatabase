package com.kunal456k.moviedatabase.views.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.kunal456k.moviedatabase.MovieDatabaseApplication;
import com.kunal456k.moviedatabase.R;
import com.kunal456k.moviedatabase.components.HomeComponent;
import com.kunal456k.moviedatabase.viewModels.BottomNavigationViewModel;
import com.kunal456k.moviedatabase.viewModels.MovieNavigationViewModel;

import javax.inject.Inject;

public class HomePage extends AppCompatActivity {
    private static final String TAG = HomePage.class.getSimpleName();

    private NavController navController;

    HomeComponent homeComponent;

    @Inject MovieNavigationViewModel movieNavigationViewModel;
    @Inject BottomNavigationViewModel bottomNavigationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeComponent = ((MovieDatabaseApplication)getApplication()).getApplicationComponent().getHomeComponent();
        homeComponent.inject(this);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        getOnBackPressedDispatcher().addCallback(this, bottomNavigationViewModel.onBackPressedCallback);
        navController = Navigation.findNavController(findViewById(R.id.movie_navigation_controller));
        movieNavigationViewModel.getMovieIdData().observe(this, this::onMovieSelected);
        bottomNavigationViewModel.getExitPress().observe(this, this::checkExitPress);
    }

    private void checkExitPress(Boolean exitPress) {
        if (exitPress){
            exitApp();
        }
    }

    private void onMovieSelected(Integer movieId) {
        if (movieId == 0){
            getOnBackPressedDispatcher().addCallback(this, bottomNavigationViewModel.onBackPressedCallback);
            navController.navigate(R.id.home_navigation_fragment);
        }else {
            Bundle bundle = new Bundle();
            bundle.putInt("movieId", movieId);
            getOnBackPressedDispatcher().addCallback(this, movieNavigationViewModel.onBackPressedCallback);
            navController.navigate(R.id.movie_details_fragment, bundle);
        }
    }

    private void exitApp() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public HomeComponent getHomeComponent() {
        return homeComponent;
    }
}