package com.kunal456k.moviedatabase.modules;

import android.app.Application;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kunal456k.moviedatabase.constants.Constants;
import com.kunal456k.moviedatabase.helpers.NetworkHelper;
import com.kunal456k.moviedatabase.services.MovieApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Cache provideHttpCache(@NonNull Application application){
        return new Cache(application.getCacheDir(), Constants.NETWORK_CACHE_SIZE);
    }

    @Provides
    @Singleton
    Gson provideGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat(Constants.RESPONSE_DATE_FORMAT);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache, Application application) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        client.addInterceptor(getOnlineInterceptor());
        client.addInterceptor(getOfflineInterceptor(application));
        client.addInterceptor(getHttpInterceptor());
        return client.build();
    }

    private Interceptor getOfflineInterceptor(Application application) {
        return chain -> {
            Request request = chain.request();
            if (!NetworkHelper.isInternetAvailable(application)){
                request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale="+ Constants.MAX_OFFLINE_VALID_CACHE)
                        .removeHeader("Pragma").build();
            }
            return chain.proceed(request);
        };
    }

    private Interceptor getHttpInterceptor(){
        return chain -> {
            HttpUrl url = chain.request().url().newBuilder()
                    .addQueryParameter("api_key", Constants.TMDB_AUTH_KEY).build();
            return chain.proceed(chain.request().newBuilder().url(url).build());
        };
    }

    private Interceptor getOnlineInterceptor() {
        return chain -> {
            Response response = chain.proceed(chain.request());
            return response.newBuilder().header("Cache-Control", "public, max-age=" + Constants.MAX_ONLINE_VALID_CACHE)
                    .removeHeader("Pragma").build();
        };
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.TMDB_API_BASE_URL)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    MovieApi provideMovieApi(@NonNull Retrofit retrofit){
        return retrofit.create(MovieApi.class);
    }
}
