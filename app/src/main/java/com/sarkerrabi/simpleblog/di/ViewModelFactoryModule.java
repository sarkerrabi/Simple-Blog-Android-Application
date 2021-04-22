package com.sarkerrabi.simpleblog.di;


import androidx.lifecycle.ViewModelProvider;


import com.sarkerrabi.simpleblog.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);

}
