package com.mercari.mercaritest.presenter;

import android.content.Context;

import com.mercari.mercaritest.R;
import com.mercari.mercaritest.data.model.Item;

import java.util.List;

public class HomePresenterImpl implements HomePresenter, DBInteractor.OnFinishedListener {

    private HomeView homeView;
    private DBInteractor dbInteractor;
    private Context context;

    public HomePresenterImpl(Context context, HomeView homeView, DBInteractor DBInteractor) {
        this.context = context;
        this.homeView = homeView;
        this.dbInteractor = DBInteractor;
        this.dbInteractor.setOnFinishedListener(this);

    }

    @Override
    public void onCreate() {
        if (homeView != null) {
            homeView.showProgress();
            homeView.setRecyclerViewColumns(context.getResources().getInteger(R.integer.column_cnt));
        }
        dbInteractor.fetchAllItems(context);
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        homeView = null;
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }

    @Override
    public void fetchAllData() {
        if (homeView != null) {
            homeView.hideErrorView();
            homeView.showProgress();
        }
        dbInteractor.fetchAllItems(context);
    }

    @Override
    public void onConfigurationChanged() {
        if (homeView != null) {
            homeView.setRecyclerViewColumns(context.getResources().getInteger(R.integer.column_cnt));
        }
    }

    public HomeView getHomeView() {
        return homeView;
    }


    @Override
    public void onFinished(List<Item> items) {
        if (homeView != null) {
            homeView.showRecyclerView();
            homeView.setAdapter(items);
            homeView.hideProgress();
            homeView.hideErrorView();
        }
    }

    @Override
    public void onError(String error) {
        if (homeView != null) {
            homeView.showErrorMessage(error);
        }
    }
}
