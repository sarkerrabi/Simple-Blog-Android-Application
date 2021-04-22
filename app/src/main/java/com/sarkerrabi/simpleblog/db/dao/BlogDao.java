package com.sarkerrabi.simpleblog.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.sarkerrabi.simpleblog.models.Author;
import com.sarkerrabi.simpleblog.models.AuthorAndBlog;
import com.sarkerrabi.simpleblog.models.Blog;

import java.util.ArrayList;
import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public abstract class BlogDao {

    @Insert(onConflict = IGNORE)
    public abstract long insertBlog(Blog blog);

    @Insert(onConflict = IGNORE)
    public abstract void insertAuthor(Author author);

    @Transaction
    public void insertBlogWithAuthor(List<Blog> blogList) {
        for (Blog blog : blogList) {
            blog.setAuthorID(blog.getAuthor().getId());
            insertBlog(blog);
            insertAuthor(blog.getAuthor());

        }
    }

    @Transaction
    @Query("SELECT * FROM Blog")
    public abstract List<AuthorAndBlog> getAllBlogAndAuthorList();

    public List<Blog> getAllBlogList() {
        List<Blog> blogList = new ArrayList<>();
        List<AuthorAndBlog> authorAndBlogs = getAllBlogAndAuthorList();
        for (AuthorAndBlog authorAndBlog : authorAndBlogs
        ) {
            Blog mBlog = new Blog();
            mBlog = authorAndBlog.blog;
            mBlog.setAuthor(authorAndBlog.author);
            blogList.add(mBlog);
        }
        return blogList;


    }

    @Query("SELECT * FROM Blog WHERE id = :id")
    public abstract AuthorAndBlog getBlogbyID(int id);
}
