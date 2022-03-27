package com.kunal456k.moviedatabase.constants;

public class Constants {
    public static final String TMDB_API_BASE_URL = "https://api.themoviedb.org/3/";
    public static final String TMDB_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";
    public static final long NETWORK_CACHE_SIZE = 30 * 1024 * 1024; //30MB
    public static final int MAX_ONLINE_VALID_CACHE = 60; //30 sec
    public static final int MAX_OFFLINE_VALID_CACHE = 30 * 24 * 60 * 60; //30 days
    public static final String TMDB_AUTH_KEY = "";
    public static final String RESPONSE_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEMO_MOVIE_DEEPLINK_ACTION = "android.intent.action.VIEW";
}
