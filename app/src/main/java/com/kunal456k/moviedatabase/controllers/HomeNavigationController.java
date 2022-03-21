package com.kunal456k.moviedatabase.controllers;

import android.app.Activity;
import android.util.Log;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.kunal456k.moviedatabase.R;

public class HomeNavigationController {

    private static final String TAG = HomeNavigationController.class.getSimpleName();

    private final NavController navController;

    public HomeNavigationController(Activity activity, int navViewId){
        navController = Navigation.findNavController(activity, navViewId);
    }


    public void navigateFragment(Integer menuId) {
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
