package com.kunal456k.moviedatabase.helpers;

import android.util.Log;

import androidx.annotation.NonNull;

import com.kunal456k.moviedatabase.Constants;

import org.jetbrains.annotations.Contract;

public class ImageLoadHelper {

    @NonNull
    @Contract(pure = true)
    public static String getSmallPosterUrl(String imagePath){
        String url = Constants.TMDB_IMAGE_BASE_URL;
        url += "/w185";
        url += imagePath;
        return url;
    }
}
