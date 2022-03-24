package com.kunal456k.moviedatabase.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kunal456k.moviedatabase.databinding.FragmentBottomNavigationBinding;
import com.kunal456k.moviedatabase.viewModels.NavigationViewModel;
import com.kunal456k.moviedatabase.views.activity.HomeActivity;

import javax.inject.Inject;

public class BottomNavigationFragment extends Fragment {

    @Inject NavigationViewModel navigationViewModel;

    private FragmentBottomNavigationBinding binding;

    public BottomNavigationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((HomeActivity)requireActivity()).getHomeComponent().inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBottomNavigationBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        binding.bottomNavigation.setOnItemSelectedListener(navigationViewModel);
        navigationViewModel.getNavigationId().observe(getViewLifecycleOwner(), selectionId -> {
            if (selectionId != binding.bottomNavigation.getSelectedItemId()){
                binding.bottomNavigation.setSelectedItemId(selectionId);
            }
        });
    }
}