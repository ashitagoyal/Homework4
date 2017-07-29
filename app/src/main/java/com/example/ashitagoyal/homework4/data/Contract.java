package com.example.ashitagoyal.homework4.data;

import android.provider.BaseColumns;

/**
 * Created by Ashita on 7/28/17.
 */
//this is the contract class used to store data for the news stories in the database
public class Contract {

    public static class TABLE_ARTICLES implements BaseColumns {
        public static final String TABLE_NAME = "articles";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_PUBLISHED_DATE = "published_date";
        public static final String COLUMN_NAME_ABSTRACT = "abstract";
        public static final String COLUMN_NAME_THUMBURL = "thumb_url";
        public static final String COLUMN_NAME_URL = "url";
    }
}
