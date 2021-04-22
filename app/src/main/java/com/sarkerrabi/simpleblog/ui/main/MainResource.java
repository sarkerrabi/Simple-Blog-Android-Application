package com.sarkerrabi.simpleblog.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MainResource<T> {

    @NonNull
    public final MainStatus status;

    @Nullable
    public final T data;


    public MainResource(@NonNull MainStatus status, @Nullable T data) {
        this.status = status;
        this.data = data;
    }

    public static <T> MainResource<T> requestSuccessful(@Nullable T data) {
        return new MainResource<>(MainStatus.SUCCESS, data);
    }

    public static <T> MainResource<T> requestFailed(@Nullable T data) {
        return new MainResource<>(MainStatus.FAILED, data);
    }

    public static <T> MainResource<T> requestLoading(@Nullable T data) {
        return new MainResource<>(MainStatus.LOADING, null);
    }


    public enum MainStatus {
        LOADING,
        SUCCESS,
        FAILED
    }

}
