package com.kunal456k.moviedatabase.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kunal456k.moviedatabase.databinding.FragmentMoviePageBinding;
import com.kunal456k.moviedatabase.viewModels.MovieDetailsViewModel;
import com.kunal456k.moviedatabase.viewModels.MovieNavigationViewModel;
import com.kunal456k.moviedatabase.views.activity.HomePage;

import javax.inject.Inject;

public class MoviePageFragment extends Fragment {

    @Inject MovieNavigationViewModel movieNavigationViewModel;
    @Inject MovieDetailsViewModel movieDetailsViewModel;

    public MoviePageFragment() {
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private View init(LayoutInflater inflater) {
        FragmentMoviePageBinding binding = FragmentMoviePageBinding.inflate(inflater);
        binding.setLifecycleOwner(this);
        int movieId = parseArguments();
        if (movieId == 0){
            movieNavigationViewModel.gotoHome();
            return binding.getRoot();
        }
        binding.setMovieDetailsViewModel(movieDetailsViewModel);
        movieNavigationViewModel.getMovieIdData().observe(getViewLifecycleOwner(), this::onMovieIdChanged);
        return binding.getRoot();
    }

    private void onMovieIdChanged(Integer movieId) {
        movieDetailsViewModel.getMovieDetails(movieId);
    }

    private int parseArguments() {
        Bundle bundle = getArguments();
        if (bundle == null) return 0;
        return bundle.getInt("movieId");
    }
}