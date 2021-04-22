package com.sarkerrabi.simpleblog.ui.blog_details;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sarkerrabi.simpleblog.db.AppDatabase;
import com.sarkerrabi.simpleblog.models.AuthorAndBlog;

import javax.inject.Inject;

public class BlogDetailsViewModel extends ViewModel {


    MutableLiveData<BlogDetailsResource<AuthorAndBlog>> mutableLiveData;

    @Inject
    AppDatabase appDatabase;

    @Inject
    public BlogDetailsViewModel() {
        mutableLiveData = new MutableLiveData<>();

    }


    public void getBlogDetailsByID(int id) {
        mutableLiveData.setValue(BlogDetailsResource.requestLoading((AuthorAndBlog) null));

        AuthorAndBlog authorAndBlog = appDatabase.getBlogDao().getBlogbyID(id);
        if (authorAndBlog == null) {
            mutableLiveData.setValue(BlogDetailsResource.requestFailed(null));
        }
        mutableLiveData.setValue(BlogDetailsResource.requestSuccessful(authorAndBlog));


    }


    public MutableLiveData<BlogDetailsResource<AuthorAndBlog>> observerBlogDetails() {
        return mutableLiveData;
    }


}

