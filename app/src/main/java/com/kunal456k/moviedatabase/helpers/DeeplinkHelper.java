package com.kunal456k.moviedatabase.helpers;

import android.content.Intent;
import android.net.Uri;

public class DeeplinkHelper {
    public static final String DEMO_MOVIE_DEEPLINK_ACTION = "android.intent.action.VIEW";
    public static final String DEMO_MOVIE_DEEPLINK_SCHEME = "demolink";
    public static final String DEMO_MOVIE_DEEPLINK_HOST = "moviedatabase";
    public static final String DEMO_MOVIE_DEEPLINK_PATH_PREFIX = "/movie";
    public static final String DEMO_MOVIE_DEEPLINK_MOVIE_QUERY_PARAM = "movieId";

    public static int getMovieIdFromDeeplink(Intent intent) {
        Uri data = intent.getData();
        if (data == null) return 0 ;
        String movieIdArg = data.getQueryParameter(DEMO_MOVIE_DEEPLINK_MOVIE_QUERY_PARAM);
        if (movieIdArg == null || movieIdArg.isEmpty()) return 0;
        try {
            return Integer.parseInt(movieIdArg);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return 0;
    }


    public static String createDeeplinkForMovie(int movieId){
        String deeplink = DEMO_MOVIE_DEEPLINK_SCHEME;
        deeplink += "://";
        deeplink += DEMO_MOVIE_DEEPLINK_HOST;
        deeplink += DEMO_MOVIE_DEEPLINK_PATH_PREFIX;
        deeplink += "?";
        deeplink += DEMO_MOVIE_DEEPLINK_MOVIE_QUERY_PARAM + "="+movieId;
        return deeplink;
    }
}
