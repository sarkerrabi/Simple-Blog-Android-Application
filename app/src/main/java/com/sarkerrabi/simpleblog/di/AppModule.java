package com.sarkerrabi.simpleblog.di;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.sarkerrabi.simpleblog.db.AppDatabase;
import com.sarkerrabi.simpleblog.util.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class AppModule {
    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance() {
        return new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build();
    }


    @Singleton
    @Provides
    static Context provideContext(Application application) {
        return application;
    }


    @Singleton
    @Provides
    static AppDatabase provideAppDatabaseInstance(Application application) {
        return Room.databaseBuilder(application.getApplicationContext(),
                AppDatabase.class, Constants.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }


}
