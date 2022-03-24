package com.kunal456k.moviedatabase.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kunal456k.moviedatabase.databinding.FragmentTrendingBinding;
import com.kunal456k.moviedatabase.viewModels.TrendingViewModel;

import javax.inject.Inject;

public class TrendingFragment extends Fragment {

    @Inject TrendingViewModel trendingViewModel;

    private FragmentTrendingBinding binding;

    public TrendingFragment() {
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTrendingBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        trendingViewModel.getTrendingMovies();
        trendingViewModel.getNowPlayingMovies();
    }
}