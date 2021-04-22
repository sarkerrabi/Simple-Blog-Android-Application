package com.sarkerrabi.simpleblog.adapters;

import android.app.Application;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sarkerrabi.simpleblog.databinding.CustomBlogItemBinding;
import com.sarkerrabi.simpleblog.models.Blog;
import com.sarkerrabi.simpleblog.models.BlogOnClickListener;
import com.sarkerrabi.simpleblog.ui.blog_details.BlogDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class BlogListAdapter extends RecyclerView.Adapter<BlogListAdapterViewHolder> {

    List<Blog> blogList = new ArrayList<>();
    Application application;
    LayoutInflater mLayoutInflater;

    @Inject
    public BlogListAdapter(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public BlogListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_blog_item, parent, false);
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }
        CustomBlogItemBinding customBlogItemBinding = CustomBlogItemBinding.inflate(mLayoutInflater, parent, false);


        return new BlogListAdapterViewHolder(customBlogItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogListAdapterViewHolder holder, int position) {
        holder.bind(blogList.get(position));

        final CustomBlogItemBinding customBlogItemBinding = holder.getCustomBlogItemBinding();
        customBlogItemBinding.setHandler(new BlogOnClickListener() {
            @Override
            public void onClickBlogItem(Blog blog) {
                Intent intent = new Intent(application.getApplicationContext(), BlogDetailsActivity.class);
                intent.putExtra("blog_id", blog.getId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                application.getApplicationContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
        notifyDataSetChanged();
    }
}
