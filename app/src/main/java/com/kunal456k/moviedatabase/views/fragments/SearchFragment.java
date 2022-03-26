package com.kunal456k.moviedatabase.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kunal456k.moviedatabase.databinding.FragmentSearchBinding;
import com.kunal456k.moviedatabase.models.Movie;
import com.kunal456k.moviedatabase.viewAdapters.MovieSearchAdapter;
import com.kunal456k.moviedatabase.viewModels.SearchViewModel;
import com.kunal456k.moviedatabase.views.activity.HomeActivity;

import java.util.List;

import javax.inject.Inject;

public class SearchFragment extends Fragment {

    @Inject SearchViewModel searchViewModel;
    @Inject MovieSearchAdapter movieSearchAdapter;

    private FragmentSearchBinding binding;

    public SearchFragment() {
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
        binding = FragmentSearchBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setSearchViewModel(searchViewModel);
        binding.setLifecycleOwner(this);
        binding.searchRecyclerView.setAdapter(movieSearchAdapter);
        searchViewModel.getSearchLiveData().observe(getViewLifecycleOwner(), this::onSearchListChanged);
    }

    private void onSearchListChanged(List<Movie> movies) {
        movieSearchAdapter.update(movies);
    }
}