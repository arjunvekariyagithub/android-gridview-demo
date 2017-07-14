package com.mercari.mercaritest.presenter;

public interface HomePresenter {

    void onCreate();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    boolean onBackPressed();

    void fetchAllData();

    void onConfigurationChanged();

}
