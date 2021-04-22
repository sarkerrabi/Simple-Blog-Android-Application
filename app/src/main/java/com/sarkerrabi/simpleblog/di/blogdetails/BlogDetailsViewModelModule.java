package com.sarkerrabi.simpleblog.di.blogdetails;

import androidx.lifecycle.ViewModel;

import com.sarkerrabi.simpleblog.di.ViewModelKey;
import com.sarkerrabi.simpleblog.ui.blog_details.BlogDetailsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class BlogDetailsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(BlogDetailsViewModel.class)
    public abstract ViewModel bindBlogDetailsViewModel(BlogDetailsViewModel blogDetailsViewModel);

}
