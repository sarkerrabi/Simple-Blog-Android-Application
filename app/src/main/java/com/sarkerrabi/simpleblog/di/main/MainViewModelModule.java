package com.sarkerrabi.simpleblog.di.main;

import androidx.lifecycle.ViewModel;

import com.sarkerrabi.simpleblog.di.ViewModelKey;
import com.sarkerrabi.simpleblog.ui.main.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindAuthViewModel(MainViewModel mainViewModel);

}
