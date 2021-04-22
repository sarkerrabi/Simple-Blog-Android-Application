package com.sarkerrabi.simpleblog.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.sarkerrabi.simpleblog.db.AppDatabase;
import com.sarkerrabi.simpleblog.models.BlogResponse;
import com.sarkerrabi.simpleblog.network.main_api.MainApi;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";
    private final MainApi mainApi;
    private MediatorLiveData<MainResource<BlogResponse>> resourceMediatorLiveData = new MediatorLiveData<>();

    @Inject
    AppDatabase appDatabase;

    @Inject
    public MainViewModel(MainApi mainApi) {
        this.mainApi = mainApi;
    }

    public void getBlogList() {
        resourceMediatorLiveData.setValue(MainResource.requestLoading((BlogResponse) null));


        final LiveData<MainResource<BlogResponse>> source = LiveDataReactiveStreams.fromPublisher(
                mainApi.getBlogListDataRequest()
                        .onErrorReturn(new Function<Throwable, BlogResponse>() {
                            @NonNull
                            @Override
                            public BlogResponse apply(@NonNull Throwable throwable) throws Exception {

                                return new BlogResponse();
                            }
                        }).map(new Function<BlogResponse, MainResource<BlogResponse>>() {
                    @NonNull
                    @Override
                    public MainResource<BlogResponse> apply(@NonNull BlogResponse blogResponse) throws Exception {

                        if (blogResponse.getBlogs()==null) {
                            blogResponse  = new BlogResponse();
                            blogResponse.setBlogs(appDatabase.getBlogDao().getAllBlogList());
                            return MainResource.requestFailed(blogResponse);
                        }
                        appDatabase.getBlogDao().insertBlogWithAuthor(blogResponse.getBlogs());
                        blogResponse.setBlogs(appDatabase.getBlogDao().getAllBlogList());

                        return MainResource.requestSuccessful(blogResponse);
                    }
                })
                        .subscribeOn(Schedulers.io())
        );

        resourceMediatorLiveData.addSource(source, new Observer<MainResource<BlogResponse>>() {
            @Override
            public void onChanged(MainResource<BlogResponse> blogResponseMainResource) {
                resourceMediatorLiveData.setValue(blogResponseMainResource);
                resourceMediatorLiveData.removeSource(source);
            }
        });
    }

    public LiveData<MainResource<BlogResponse>> observeBlogList() {
        return resourceMediatorLiveData;
    }

}
