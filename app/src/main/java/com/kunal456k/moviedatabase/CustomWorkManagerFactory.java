package com.kunal456k.moviedatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerFactory;
import androidx.work.WorkerParameters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

public class CustomWorkManagerFactory extends WorkerFactory {

    @Inject
    public CustomWorkManagerFactory( ){

    }

    @Nullable
    @Override
    public ListenableWorker createWorker(@NonNull Context appContext, @NonNull String workerClassName, @NonNull WorkerParameters workerParameters) {
        try {
            Class<?> workerClass = Class.forName(workerClassName).asSubclass(Worker.class);
            Constructor<?> constructor = workerClass.getDeclaredConstructor(Context.class, WorkerParameters.class);
            constructor.newInstance(appContext, workerParameters);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

}
