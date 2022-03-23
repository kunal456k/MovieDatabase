package com.kunal456k.moviedatabase.views;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kunal456k.moviedatabase.R;
import com.kunal456k.moviedatabase.viewModels.NavigationViewModel;

import javax.inject.Inject;

public class BottomNavigationFragment extends Fragment {

    @Inject NavigationViewModel navigationViewModel;

    public BottomNavigationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((HomeActivity)requireActivity()).homeComponent.inject(this);
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
        init(view);
    }

    private void init(View view) {
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(navigationViewModel);
        navigationViewModel.getNavigationId().observe(getViewLifecycleOwner(), selectionId -> {
            if (selectionId != bottomNavigationView.getSelectedItemId()){
                bottomNavigationView.setSelectedItemId(selectionId);
            }
        });
    }
}