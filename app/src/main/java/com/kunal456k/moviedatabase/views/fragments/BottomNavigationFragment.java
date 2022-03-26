package com.kunal456k.moviedatabase.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.kunal456k.moviedatabase.databinding.FragmentBottomNavigationBinding;
import com.kunal456k.moviedatabase.viewModels.BottomNavigationViewModel;
import com.kunal456k.moviedatabase.views.activity.HomePage;

import javax.inject.Inject;

public class BottomNavigationFragment extends Fragment {

    @Inject
    BottomNavigationViewModel bottomNavigationViewModel;

    public BottomNavigationFragment() {
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
        FragmentBottomNavigationBinding binding = FragmentBottomNavigationBinding.inflate(inflater);
        binding.bottomNavigation.setOnItemSelectedListener(bottomNavigationViewModel);
        bottomNavigationViewModel.getNavigationId().observe(getViewLifecycleOwner(), selectionId -> {
            if (selectionId != binding.bottomNavigation.getSelectedItemId()){
                binding.bottomNavigation.setSelectedItemId(selectionId);
            }
        });
        return binding.getRoot();
    }
}