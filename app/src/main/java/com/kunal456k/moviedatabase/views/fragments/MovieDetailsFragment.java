package com.kunal456k.moviedatabase.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kunal456k.moviedatabase.databinding.FragmentMovieDetailsBinding;
import com.kunal456k.moviedatabase.models.MovieDetails;
import com.kunal456k.moviedatabase.viewModels.MovieDetailsViewModel;
import com.kunal456k.moviedatabase.viewModels.MovieNavigationViewModel;
import com.kunal456k.moviedatabase.views.activity.HomePage;

import javax.inject.Inject;

public class MovieDetailsFragment extends Fragment {

    @Inject MovieNavigationViewModel movieNavigationViewModel;
    @Inject MovieDetailsViewModel movieDetailsViewModel;

    private FragmentMovieDetailsBinding binding;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((HomePage)requireActivity()).getHomeComponent().inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int movieId = parseArguments();
        if (movieId == 0){
            movieNavigationViewModel.gotoHome();
            return;
        }
        init(movieId);
    }

    private void init(int movieId) {
        movieNavigationViewModel.getMovieIdData().observe(getViewLifecycleOwner(), this::onMovieIdChanged);
        movieDetailsViewModel.getMovieDetails(movieId).observe(getViewLifecycleOwner(), this::onMovieDetails);
    }

    private void onMovieIdChanged(Integer movieId) {
        if (movieId == 0){
            binding.setImageUrl(null);
            movieDetailsViewModel.clearMovieDetails();
        }
    }

    private void onMovieDetails(MovieDetails movieDetails) {
        if (movieDetails == null) return;
        binding.setImageUrl(movieDetails.getBackdropPath());
    }

    private int parseArguments() {
        Bundle bundle = getArguments();
        if (bundle == null) return 0;
        return bundle.getInt("movieId");
    }
}