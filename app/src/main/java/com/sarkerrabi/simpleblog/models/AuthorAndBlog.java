package com.sarkerrabi.simpleblog.models;

import androidx.room.Embedded;
import androidx.room.Relation;

public class AuthorAndBlog {
    @Embedded
    public Blog blog;
    @Relation(
            parentColumn = "authorID",
            entityColumn = "id",
            entity = Author.class
    )
    public Author author;

}
