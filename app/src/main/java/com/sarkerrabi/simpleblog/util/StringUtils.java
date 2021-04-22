package com.sarkerrabi.simpleblog.util;

import java.util.List;

public class StringUtils {
    public static String getCategories(List<String> categories) {
        String mCategories = "";
        if (categories != null) {

            if (categories.size() == 0) {
                return mCategories;
            } else if (categories.size() == 1) {
                mCategories = categories.get(0);
                return mCategories;
            } else {
                mCategories = categories.get(0);
                for (int i = 1; i < categories.size(); i++) {
                    mCategories = mCategories + " , " + categories.get(i);
                }

            }

        }
        return mCategories;
    }

}
