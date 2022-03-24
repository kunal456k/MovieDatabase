package com.kunal456k.moviedatabase.modules;

import android.app.Application;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kunal456k.moviedatabase.Constants;
import com.kunal456k.moviedatabase.services.MovieApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
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
    Interceptor provideHttpInterceptor(){
        return chain -> {
            HttpUrl url = chain.request().url().newBuilder()
                    .addQueryParameter("api_key", Constants.TMDB_AUTH_KEY).build();
            return chain.proceed(chain.request().newBuilder().url(url).build());
        };
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache, Interceptor interceptor) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        client.addInterceptor(interceptor);
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
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
