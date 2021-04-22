package com.sarkerrabi.simpleblog.network.main_api;

import com.sarkerrabi.simpleblog.models.BlogResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface MainApi {

    @GET("db")
    Flowable<BlogResponse> getBlogListDataRequest();



}
