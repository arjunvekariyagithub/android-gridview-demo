package com.mercari.mercaritest.presenter;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.mercari.mercaritest.data.model.Response;
import com.mercari.mercaritest.rest.ApiInterface;
import com.mercari.mercaritest.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DBInteractorImpl implements DBInteractor {

    public OnFinishedListener onFinishedListener =  null;
    private Response response;
    private static Context sContext;
    public ApiInterface apiService;

    public DBInteractorImpl(ApiInterface apiService) {
        this.apiService = apiService;
    }

    public void setOnFinishedListener(OnFinishedListener listener) {
        onFinishedListener = listener;
    }

    @Override
    public void fetchAllItems(final Context context) {
        sContext = context;

        /*
            For future use, in case we need to fetch the data from server.
            Using Retrofit as HTTP client and RxJava to handle async network call.

        apiService.search4Term("Men")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response == null || response.getData().size() == 0) {
                        onFinishedListener.onError(Utils.fetchErrorMessage(context, null));
                    } else {
                        onFinishedListener.onFinished(response.getData());
                    }
                }, throwable -> onFinishedListener.onError(Utils.fetchErrorMessage(context, throwable)));

        */

        new Handler().post(new Runnable() {
            @Override public void run() {
                if (onFinishedListener != null) {
                    StringBuilder content = new StringBuilder();
                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(
                                new InputStreamReader(context.getAssets().open("all.json")));

                        String line;
                        while ((line = reader.readLine()) != null) {
                            content.append(line);
                        }
                    } catch (IOException e) {
                        Utils.showLongToast(context, e.toString());
                    } finally {
                        if (reader != null) {
                            try {
                                reader.close();
                            } catch (IOException e) {
                                Utils.showLongToast(context, e.toString());
                            }
                        }
                    }

                    Gson gson = new Gson();
                    response = gson.fromJson(content.toString(), Response.class);
                    if (response == null || response.getData().size() == 0) {
                        onFinishedListener.onError(Utils.fetchErrorMessage(context, null));
                    } else {
                        onFinishedListener.onFinished(response.getData());
                    }
                }
            }
        });
    }
}
