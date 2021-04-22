package com.sarkerrabi.simpleblog.ui.create_blog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CreateBlogResource<T> {

    @NonNull
    public final CreateBlogStatus status;

    @Nullable
    public final String msg;

    public CreateBlogResource(@NonNull CreateBlogStatus status, @Nullable String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static CreateBlogResource requestSuccessful(@Nullable String msg) {
        return new CreateBlogResource(CreateBlogStatus.SUCCESS, msg);
    }

    public static CreateBlogResource requestFailed(@Nullable String msg) {
        return new CreateBlogResource(CreateBlogStatus.FAILED, msg);
    }

    public static CreateBlogResource requestLoading() {
        return new CreateBlogResource(CreateBlogStatus.LOADING, null);
    }


    public enum CreateBlogStatus {
        LOADING,
        SUCCESS,
        FAILED
    }

}
