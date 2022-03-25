package com.kunal456k.moviedatabase.modules;

import android.app.Application;

import androidx.work.WorkManager;

import com.kunal456k.moviedatabase.CustomWorkManagerFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class WorkerModule {

    public WorkerModule(){

    }

    @Provides
    @Singleton
    CustomWorkManagerFactory providesCustomWorkerFactory(){
        return new CustomWorkManagerFactory();
    }

    @Provides
    @Singleton
    WorkManager providesWorkManager(Application application){
        return WorkManager.getInstance(application);
    }
}
