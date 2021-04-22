package com.sarkerrabi.simpleblog.di.create_blog;

import androidx.lifecycle.ViewModel;

import com.sarkerrabi.simpleblog.di.ViewModelKey;
import com.sarkerrabi.simpleblog.ui.create_blog.CreateBlogViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class CreateBlogViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CreateBlogViewModel.class)
    public abstract ViewModel bindCreateBlogViewModel(CreateBlogViewModel createBlogViewModel);

}
