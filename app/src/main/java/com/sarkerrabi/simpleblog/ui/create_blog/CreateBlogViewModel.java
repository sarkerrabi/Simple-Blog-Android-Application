package com.sarkerrabi.simpleblog.ui.create_blog;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sarkerrabi.simpleblog.db.AppDatabase;
import com.sarkerrabi.simpleblog.models.Blog;

import javax.inject.Inject;

public class CreateBlogViewModel extends ViewModel {
    private static final String TAG = "CreateBlogViewModel";
    @Inject
    AppDatabase appDatabase;

    MutableLiveData<CreateBlogResource> mutableLiveData;

    @Inject
    public CreateBlogViewModel() {
        mutableLiveData = new MutableLiveData<>();
    }


    public void createBlogPost(Blog createdBlog) {
        mutableLiveData.setValue(CreateBlogResource.requestLoading());

        if (appDatabase.getBlogDao().insertBlog(createdBlog)>0){
            mutableLiveData.setValue(CreateBlogResource.requestSuccessful("Blog post has been created successfully"));
        }
        mutableLiveData.setValue(CreateBlogResource.requestFailed("Something wrong !! Please try again"));
    }

    public MutableLiveData<CreateBlogResource> getMutableLiveData() {
        return mutableLiveData;
    }
}

