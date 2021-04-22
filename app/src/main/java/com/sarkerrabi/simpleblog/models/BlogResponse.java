package com.sarkerrabi.simpleblog.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BlogResponse {

    @SerializedName("blogs")
    @Expose
    public List<Blog> blogs = null;

    public List<Blog> getBlogs() {
        return this.blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
