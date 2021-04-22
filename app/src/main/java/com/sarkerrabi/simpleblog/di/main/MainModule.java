package com.sarkerrabi.simpleblog.di.main;


import android.app.Application;

import com.sarkerrabi.simpleblog.adapters.BlogListAdapter;
import com.sarkerrabi.simpleblog.network.main_api.MainApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {


    @Provides
    static BlogListAdapter provideAdapter(Application application){
        return new BlogListAdapter(application);
    }

    @Provides
    static MainApi provideAuthApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }


}
