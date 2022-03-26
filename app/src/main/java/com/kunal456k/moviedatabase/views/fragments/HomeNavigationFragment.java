package com.kunal456k.moviedatabase.views.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kunal456k.moviedatabase.R;
import com.kunal456k.moviedatabase.databinding.FragmentHomenavigationBinding;
import com.kunal456k.moviedatabase.viewModels.BottomNavigationViewModel;
import com.kunal456k.moviedatabase.views.activity.HomePage;

import javax.inject.Inject;

public class HomeNavigationFragment extends Fragment {
    private static final String TAG = HomeNavigationFragment.class.getSimpleName();

    @Inject
    BottomNavigationViewModel bottomNavigationViewModel;

    private NavController navController;

    public HomeNavigationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((HomePage)requireActivity()).getHomeComponent().inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return init(inflater);
    }

    private View init(LayoutInflater inflater) {
        FragmentHomenavigationBinding homeNavigationBinding = FragmentHomenavigationBinding.inflate(inflater);
        bottomNavigationViewModel.getNavigationId().observe(getViewLifecycleOwner(), this::navigateFragments);
        navController = Navigation.findNavController(homeNavigationBinding.getRoot().findViewById(R.id.home_navigation_controller));
        return homeNavigationBinding.getRoot();
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