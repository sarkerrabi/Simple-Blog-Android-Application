package com.sarkerrabi.simpleblog.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.sarkerrabi.simpleblog.R;
import com.sarkerrabi.simpleblog.adapters.BlogListAdapter;
import com.sarkerrabi.simpleblog.databinding.ActivityMainBinding;
import com.sarkerrabi.simpleblog.models.BlogResponse;
import com.sarkerrabi.simpleblog.ui.create_blog.CreateBlogActivity;
import com.sarkerrabi.simpleblog.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {
    private static final String TAG = "MainActivity";
    private MainViewModel mainViewModel;

    ActivityMainBinding mActivityMainBinding;

    @Inject
    ViewModelProviderFactory providerFactory;


    @Inject
    BlogListAdapter blogListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel.class);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mActivityMainBinding.setActivity(this);
        ButterKnife.bind(this);
        initRecyclerView();
        subscribeObservers();


    }

    @Override
    protected void onResume() {
        super.onResume();
        mainViewModel.getBlogList();
    }

    private void initRecyclerView() {
        mActivityMainBinding.rvBlogList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mActivityMainBinding.rvBlogList.setAdapter(blogListAdapter);
    }

    private void subscribeObservers() {
        mainViewModel.observeBlogList().observe(this, new Observer<MainResource<BlogResponse>>() {
            @Override
            public void onChanged(MainResource<BlogResponse> blogResponseMainResource) {
                if (blogResponseMainResource != null) {
                    switch (blogResponseMainResource.status) {
                        case LOADING:
                            showProgressBar(true);
                            break;
                        case FAILED:
                            showProgressBar(false);
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            blogListAdapter.setBlogList(blogResponseMainResource.data.getBlogs());
                            break;
                        case SUCCESS:
                            showProgressBar(false);
                            blogListAdapter.setBlogList(blogResponseMainResource.data.getBlogs());
                            break;
                    }
                }
            }
        });
    }


    private void showProgressBar(boolean isShowing) {
        if (isShowing) {
            mActivityMainBinding.progressBar.setVisibility(View.VISIBLE);
        } else {
            mActivityMainBinding.progressBar.setVisibility(View.GONE);
        }

    }

    public void onCreateBlogClicked(View view) {
        Intent intent = new Intent(this, CreateBlogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}