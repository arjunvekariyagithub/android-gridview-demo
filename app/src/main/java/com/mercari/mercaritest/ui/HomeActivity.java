package com.mercari.mercaritest.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mercari.mercaritest.MercariApp;
import com.mercari.mercaritest.R;
import com.mercari.mercaritest.adapter.ProductListAdapter;
import com.mercari.mercaritest.data.model.Item;
import com.mercari.mercaritest.presenter.DBInteractorImpl;
import com.mercari.mercaritest.presenter.HomePresenterImpl;
import com.mercari.mercaritest.presenter.HomeView;
import com.mercari.mercaritest.rest.ApiInterface;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView {

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    @BindView(R.id.progress_bar)
    public ProgressBar progressBar;

    @BindView(R.id.error_layout)
    public LinearLayout error_layout;

    @BindView(R.id.error_btn_retry)
    public Button btn_retry;

    @BindView(R.id.error_txt_cause)
    public TextView text_error;

    private Context context;
    private HomePresenterImpl presenter;
    private ProductListAdapter adapter;

    @Inject public ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        MercariApp.getAppComponent().inject(this);

        context = this;
        presenter = new HomePresenterImpl(context, this, new DBInteractorImpl(apiService));
        presenter.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        presenter.onConfigurationChanged();
    }

    @Override
    public void showProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showRecyclerView() {
        if (recyclerView != null) {
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideRecyclerView() {
        if (recyclerView != null) {
            recyclerView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showErrorMessage(String message) {
        hideRecyclerView();
        hideProgress();
        if (error_layout != null) {
            error_layout.setVisibility(View.VISIBLE);
            text_error.setText(message);
        }
    }

    @Override
    public void hideErrorView() {
        if (error_layout != null) {
            error_layout.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setAdapter(List<Item> items) {
        if (recyclerView != null) {
            adapter = new ProductListAdapter(items, context);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void setRecyclerViewColumns(int cnt) {
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new GridLayoutManager(context, cnt));
        }
    }
}
