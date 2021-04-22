package com.sarkerrabi.simpleblog.adapters;

import androidx.recyclerview.widget.RecyclerView;

import com.sarkerrabi.simpleblog.databinding.CustomBlogItemBinding;
import com.sarkerrabi.simpleblog.models.Blog;

public class BlogListAdapterViewHolder extends RecyclerView.ViewHolder {

    private CustomBlogItemBinding mCustomBlogItemBinding;

    public BlogListAdapterViewHolder(CustomBlogItemBinding blogItemBinding) {
        super(blogItemBinding.getRoot());
        this.mCustomBlogItemBinding = blogItemBinding;
    }


    public void bind(Blog blog) {
        mCustomBlogItemBinding.setBlog(blog);

    }

    public CustomBlogItemBinding getCustomBlogItemBinding() {
        return mCustomBlogItemBinding;
    }
}
