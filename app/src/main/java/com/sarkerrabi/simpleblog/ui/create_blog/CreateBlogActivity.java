package com.sarkerrabi.simpleblog.ui.create_blog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sarkerrabi.simpleblog.R;
import com.sarkerrabi.simpleblog.databinding.ActivityCreateBlogBinding;
import com.sarkerrabi.simpleblog.db.AppDatabase;
import com.sarkerrabi.simpleblog.models.Blog;
import com.sarkerrabi.simpleblog.viewmodels.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class CreateBlogActivity extends DaggerAppCompatActivity {

    private static final String TAG = "CreateBlogActivity";
    CreateBlogViewModel createBlogViewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    ActivityCreateBlogBinding mActivityCreateBlogBinding;
    private Blog createdBlog = new Blog();


    @Inject
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.create_blog_post);
        createBlogViewModel = ViewModelProviders.of(this, providerFactory).get(CreateBlogViewModel.class);
        mActivityCreateBlogBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_blog);
        mActivityCreateBlogBinding.setCreateBlog(this);
        subscribeObserver();

    }

    private void subscribeObserver() {
        createBlogViewModel.getMutableLiveData().observe(this, new Observer<CreateBlogResource>() {
            @Override
            public void onChanged(CreateBlogResource createBlogResource) {
                if (createBlogResource!=null){
                    switch (createBlogResource.status) {
                        case LOADING:
                            showProgressBar(true);
                            break;
                        case FAILED:
                            showProgressBar(false);
                            Toast.makeText(CreateBlogActivity.this, createBlogResource.msg, Toast.LENGTH_SHORT).show();
                            break;
                        case SUCCESS:
                            showProgressBar(false);
                            Toast.makeText(CreateBlogActivity.this, createBlogResource.msg, Toast.LENGTH_SHORT).show();
                            finish();
                            break;
                    }
                }
            }
        });
    }

    public void onCreateBlogSubmitClicked(View view) {

        String tittle = mActivityCreateBlogBinding.etTitle.getText().toString().trim();
        if (TextUtils.isEmpty(tittle)) {
            mActivityCreateBlogBinding.etTitle.setError("Please add a title");
        }
        createdBlog.setTitle(tittle);

        String desc = mActivityCreateBlogBinding.etDescription.getText().toString().trim();
        if (TextUtils.isEmpty(desc)) {
            mActivityCreateBlogBinding.etDescription.setError("Please add a description");
        }
        createdBlog.setDescription(desc);

        List<String> categories = new ArrayList<>();
        if (mActivityCreateBlogBinding.cbBusiness.isChecked()) {
            categories.add("Business");
        }
        if (mActivityCreateBlogBinding.cbLifestyle.isChecked()) {
            categories.add("Lifestyle");
        }

        createdBlog.setCategories(categories);
        createdBlog.setAuthorID(1);
        createBlogViewModel.createBlogPost(createdBlog);


    }

    private void showProgressBar(boolean isShowing) {
        if (isShowing) {
            mActivityCreateBlogBinding.progressBarCreatePost.setVisibility(View.VISIBLE);
        } else {
            mActivityCreateBlogBinding.progressBarCreatePost.setVisibility(View.GONE);
        }

    }

}