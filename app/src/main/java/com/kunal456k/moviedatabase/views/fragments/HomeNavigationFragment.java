package com.kunal456k.moviedatabase.views.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kunal456k.moviedatabase.R;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homenavigation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        bottomNavigationViewModel.getNavigationId().observe(getViewLifecycleOwner(), this::navigateFragments);
        navController = Navigation.findNavController(view.findViewById(R.id.home_navigation_controller));
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