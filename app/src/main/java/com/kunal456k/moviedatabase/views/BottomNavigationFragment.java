package com.kunal456k.moviedatabase.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kunal456k.moviedatabase.R;
import com.kunal456k.moviedatabase.viewModels.NavigationViewModel;

public class BottomNavigationFragment extends Fragment {

    public BottomNavigationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_navigation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavigationViewModel navigationViewModel = new ViewModelProvider(requireActivity()).get(NavigationViewModel.class);
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(navigationViewModel);
        navigationViewModel.getNavigationId().observe(getViewLifecycleOwner(), selectionId -> {
            if (selectionId != bottomNavigationView.getSelectedItemId()){
                bottomNavigationView.setSelectedItemId(selectionId);
            }
        });
    }
}