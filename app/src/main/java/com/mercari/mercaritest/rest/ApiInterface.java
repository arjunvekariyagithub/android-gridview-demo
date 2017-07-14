package com.mercari.mercaritest.rest;

import com.mercari.mercaritest.data.model.Response;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {

    /**
     * retrofit callback signature for retrieving product images.
     *
     * @param term   search term
     * @return data in {@link Response} form
     */
    @GET("Search")
    Observable<Response> search4Term(@Query("term") String term);

}
