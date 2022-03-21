package com.kunal456k.moviedatabase.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.kunal456k.moviedatabase.R;
import com.kunal456k.moviedatabase.controllers.HomeNavigationController;
import com.kunal456k.moviedatabase.viewModels.NavigationViewModel;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = HomeActivity.class.getSimpleName();

    private HomeNavigationController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initModels();
        navController = new HomeNavigationController(this, R.id.home_content_view);
    }

    private void initModels() {
        NavigationViewModel navigationViewModel = new ViewModelProvider(this).get(NavigationViewModel.class);
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
        navController.navigateFragment(menuId);
    }
}