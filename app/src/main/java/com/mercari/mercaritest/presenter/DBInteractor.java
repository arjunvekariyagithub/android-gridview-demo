package com.mercari.mercaritest.presenter;

import android.content.Context;

import com.mercari.mercaritest.data.model.Item;

import java.util.List;

public interface DBInteractor {

    interface OnFinishedListener {
        void onFinished(List<Item> items);
        void onError(String error);
    }

    void fetchAllItems(Context context);

    void setOnFinishedListener(OnFinishedListener listener);
}
