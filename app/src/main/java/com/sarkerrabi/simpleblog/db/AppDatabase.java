/**************************************************************************************************
 * *                                                                                              *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group                          *
 *  * Created on on 12/26/20 5:13 PM                                                              *
 *  * Copyright (c) 2020 . All rights reserved.                                                   *
 *  * Last modified 12/26/20 4:01 PM                                                              *
 *                                                                                                *
 **************************************************************************************************/

package com.sarkerrabi.simpleblog.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.sarkerrabi.simpleblog.db.dao.BlogDao;
import com.sarkerrabi.simpleblog.models.Author;
import com.sarkerrabi.simpleblog.models.Blog;
import com.sarkerrabi.simpleblog.models.Converters;


@Database(entities =
        {
                Blog.class,
                Author.class


        }, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {


    public abstract BlogDao getBlogDao();


}


