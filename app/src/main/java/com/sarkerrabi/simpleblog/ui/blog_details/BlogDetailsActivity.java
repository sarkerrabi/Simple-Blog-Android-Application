package com.sarkerrabi.simpleblog.ui.blog_details;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sarkerrabi.simpleblog.R;
import com.sarkerrabi.simpleblog.databinding.ActivityBlogDetailsBinding;
import com.sarkerrabi.simpleblog.models.AuthorAndBlog;
import com.sarkerrabi.simpleblog.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class BlogDetailsActivity extends DaggerAppCompatActivity {
    BlogDetailsViewModel blogDetailsViewModel;
    int blog_id = 0;

    ActivityBlogDetailsBinding mBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.show_blog_details);
        blogDetailsViewModel = ViewModelProviders.of(this, providerFactory).get(BlogDetailsViewModel.class);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_blog_details);

        subscribeObserver();

        blog_id = getIntent().getIntExtra("blog_id", -1);
        if (blog_id == -1) {
            finish();
            Toast.makeText(this, "Something Wrong!!", Toast.LENGTH_SHORT).show();
        }
        blogDetailsViewModel.getBlogDetailsByID(blog_id);
    }

    private void subscribeObserver() {
        blogDetailsViewModel.observerBlogDetails().observe(this, new Observer<BlogDetailsResource<AuthorAndBlog>>() {
            @Override
            public void onChanged(BlogDetailsResource<AuthorAndBlog> blogDetailsResource) {
                if (blogDetailsResource != null) {
                    switch (blogDetailsResource.status) {
                        case LOADING:
                            showProgressBar(true);
                            break;
                        case SUCCESS:
                            showProgressBar(false);

                            mBinding.setAuthorAndBlog(blogDetailsResource.data);
                            break;
                        case FAILED:
                            showProgressBar(false);
                            Toast.makeText(BlogDetailsActivity.this, "Something wrong!!", Toast.LENGTH_SHORT).show();

                            finish();
                            break;
                    }
                }
            }
        });


    }

    private void showProgressBar(boolean isShowing) {
        if (isShowing) {
            mBinding.progressBarDetails.setVisibility(View.VISIBLE);
        } else {
            mBinding.progressBarDetails.setVisibility(View.GONE);
        }

    }

}