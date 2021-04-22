package com.sarkerrabi.simpleblog.ui.blog_details;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BlogDetailsResource<T> {

    @NonNull
    public final BlogDetailsStatus status;

    @Nullable
    public final T data;


    public BlogDetailsResource(@NonNull BlogDetailsStatus status, @Nullable T data) {
        this.status = status;
        this.data = data;
    }

    public static <T> BlogDetailsResource<T> requestSuccessful(@Nullable T data) {
        return new BlogDetailsResource<>(BlogDetailsStatus.SUCCESS, data);
    }

    public static <T> BlogDetailsResource<T> requestFailed(@Nullable T data) {
        return new BlogDetailsResource<>(BlogDetailsStatus.FAILED, null);
    }

    public static <T> BlogDetailsResource<T> requestLoading(@Nullable T data) {
        return new BlogDetailsResource<>(BlogDetailsStatus.LOADING, null);
    }


    public enum BlogDetailsStatus {
        LOADING,
        SUCCESS,
        FAILED
    }

}
