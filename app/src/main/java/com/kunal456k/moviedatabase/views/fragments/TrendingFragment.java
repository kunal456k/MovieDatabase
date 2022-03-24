package com.kunal456k.moviedatabase.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kunal456k.moviedatabase.databinding.FragmentTrendingBinding;
import com.kunal456k.moviedatabase.models.Movie;
import com.kunal456k.moviedatabase.viewModels.TrendingViewModel;
import com.kunal456k.moviedatabase.views.activity.HomeActivity;
import com.kunal456k.moviedatabase.views.adapters.MovieAdapter;

import java.util.List;

import javax.inject.Inject;


public class TrendingFragment extends Fragment {

    @Inject
    TrendingViewModel trendingViewModel;

    private FragmentTrendingBinding binding;
    private MovieAdapter adapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((HomeActivity)requireActivity()).getHomeComponent().inject(this);
    }

    public TrendingFragment() {
        // Required empty public constructor
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
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setTrendingViewModel(trendingViewModel);
        trendingViewModel.getTrendingMovies().observe(getViewLifecycleOwner(), this::onTrendingMoviesUpdate);
    }

    private void onTrendingMoviesUpdate(List<Movie> movies) {
        if (adapter == null){
            adapter = new MovieAdapter(movies);
            binding.trendingNowRecycler.setAdapter(adapter);
        }else {
            adapter.update(movies);
        }
    }
}