package com.sarkerrabi.simpleblog.di;

import com.sarkerrabi.simpleblog.di.blogdetails.BlogDetailsViewModelModule;
import com.sarkerrabi.simpleblog.di.create_blog.CreateBlogViewModelModule;
import com.sarkerrabi.simpleblog.di.main.MainModule;
import com.sarkerrabi.simpleblog.di.main.MainViewModelModule;
import com.sarkerrabi.simpleblog.ui.blog_details.BlogDetailsActivity;
import com.sarkerrabi.simpleblog.ui.create_blog.CreateBlogActivity;
import com.sarkerrabi.simpleblog.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(
            modules = {
                    MainViewModelModule.class,
                    MainModule.class,
            }
    )
    abstract MainActivity contributeAuthActivity();


    @ContributesAndroidInjector(
            modules = BlogDetailsViewModelModule.class
    )
    abstract BlogDetailsActivity contributeBlogDetailsActivity();

    @ContributesAndroidInjector(
            modules = CreateBlogViewModelModule.class
    )
    abstract CreateBlogActivity contributeCreateBlogActivity();

}
