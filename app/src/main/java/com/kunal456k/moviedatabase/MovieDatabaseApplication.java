package com.kunal456k.moviedatabase;

import android.app.Application;

import androidx.work.Configuration;
import androidx.work.WorkManager;

import com.kunal456k.moviedatabase.components.ApplicationComponent;
import com.kunal456k.moviedatabase.components.DaggerApplicationComponent;
import com.kunal456k.moviedatabase.modules.AppModule;
import com.kunal456k.moviedatabase.modules.NetworkModule;
import com.kunal456k.moviedatabase.modules.WorkerModule;

import javax.inject.Inject;

public class MovieDatabaseApplication extends Application {

    ApplicationComponent applicationComponent;

    @Inject CustomWorkManagerFactory workManagerFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .networkModule(new NetworkModule())
                .appModule(new AppModule(this))
                .workerModule(new WorkerModule())
                .build();
        applicationComponent.inject(this);
        Configuration configuration = new Configuration.Builder().setWorkerFactory(workManagerFactory)
                .build();
        WorkManager.initialize(this, configuration);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
