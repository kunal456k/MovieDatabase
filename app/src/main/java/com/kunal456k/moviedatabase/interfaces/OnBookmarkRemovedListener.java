package com.kunal456k.moviedatabase.interfaces;

import com.kunal456k.moviedatabase.db.entities.Movie;

public interface OnBookmarkRemovedListener {
    void onBookmarkRemoved(Movie movie);
}
