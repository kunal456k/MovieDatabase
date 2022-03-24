package com.kunal456k.moviedatabase;

import android.app.Application;

import com.kunal456k.moviedatabase.components.ApplicationComponent;
import com.kunal456k.moviedatabase.components.DaggerApplicationComponent;
import com.kunal456k.moviedatabase.modules.AppModule;
import com.kunal456k.moviedatabase.modules.NetworkModule;

public class MovieDatabaseApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .networkModule(new NetworkModule())
                .appModule(new AppModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
