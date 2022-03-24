package com.kunal456k.moviedatabase.helpers;

import androidx.recyclerview.widget.DiffUtil;

import com.kunal456k.moviedatabase.models.Movie;

import java.util.List;

public class MovieDiffUtillCallback extends DiffUtil.Callback{

    List<Movie> newList;
    List<Movie> oldList;

    public MovieDiffUtillCallback(List<Movie> oldList, List<Movie> newList) {
        this.newList = newList;
        this.oldList = oldList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getMovieId() == newList.get(newItemPosition).getMovieId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Movie oldItem = oldList.get(oldItemPosition);
        Movie newItem = newList.get(newItemPosition);
        return oldItem.getMovieId() == newItem.getMovieId() &&
                oldItem.getTitle().equals(newItem.getTitle()) &&
                oldItem.getRating() == newItem.getRating();
    }
}
