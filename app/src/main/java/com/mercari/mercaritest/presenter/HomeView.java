package com.mercari.mercaritest.presenter;

import com.mercari.mercaritest.data.model.Item;

import java.util.List;

public interface HomeView {

    void showProgress();

    void hideProgress();

    void showRecyclerView();

    void hideRecyclerView();

    void hideErrorView();

    void showErrorMessage(String message);

    void setAdapter(List<Item> items);

    void setRecyclerViewColumns(int cnt);
}
