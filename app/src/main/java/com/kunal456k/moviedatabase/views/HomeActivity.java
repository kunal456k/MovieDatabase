package com.kunal456k.moviedatabase.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.kunal456k.moviedatabase.R;
import com.kunal456k.moviedatabase.components.DaggerHomeComponent;
import com.kunal456k.moviedatabase.components.HomeComponent;
import com.kunal456k.moviedatabase.viewModels.NavigationViewModel;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = HomeActivity.class.getSimpleName();

    private NavController navController;

    HomeComponent homeComponent;

    @Inject NavigationViewModel navigationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeComponent = DaggerHomeComponent.create();
        homeComponent.inject(this);
        setContentView(R.layout.activity_main);
        initModels();
        navController = Navigation.findNavController(this, R.id.home_content_view);
    }

    private void initModels() {
        navigationViewModel.getNavigationId().observe(this, this::navigateFragments);
        navigationViewModel.getExitPress().observe(this, this::checkBackPress);
        getOnBackPressedDispatcher().addCallback(this, navigationViewModel.getOnBackPressCallBack());
    }

    private void checkBackPress(Boolean exitPress) {
        if (exitPress){
            exitApp();
        }
    }

    private void exitApp() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void navigateFragments(Integer menuId) {
        if (menuId == R.id.navigation_home){
            navController.navigate(R.id.trending_fragment);
        }else if (menuId == R.id.navigation_search){
            navController.navigate(R.id.search_fragment);
        }else if (menuId == R.id.navigation_bookmark){
            navController.navigate(R.id.bookmarks_fragment);
        }else {
            Log.d(TAG, "navigateFragment: unknown navigation request");
        }
    }
}