package com.mercari.mercaritest;

import android.app.Application;

import com.mercari.mercaritest.di.AppComponent;


public class MercariApp extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = AppComponent.Initializer.init(this);
        appComponent.inject(this);
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
