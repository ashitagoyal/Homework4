package com.example.ashitagoyal.homework4.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
/**
 * Created by Ashita on 7/28/17.
 */

public class DatabaseUtils {
    /**
     *
     * @param db: db is an argument used to get data stored in table created using SQLiteDatabase.
     * @return: the data fetched using th query.
     */

    public static Cursor getAll(SQLiteDatabase db) {
        Cursor cursor = db.query(
                Contract.TABLE_ARTICLES.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                Contract.TABLE_ARTICLES.COLUMN_NAME_PUBLISHED_DATE + " DESC"
        );
        return cursor;
    }

    /**
     *
     * @param db: reference of the database created using SQLiteDatabase.
     * @param articles:reference to the arraylist of articles.
     *                This method is used to insert the data stored in arraylist in the database.
     */
    public static void bulkInsert(SQLiteDatabase db, ArrayList<Article> articles) {

        db.beginTransaction();
        try {
            for (Article a : articles) {
                ContentValues cv = new ContentValues();
                cv.put(Contract.TABLE_ARTICLES.COLUMN_NAME_TITLE, a.getTitle());
                cv.put(Contract.TABLE_ARTICLES.COLUMN_NAME_ABSTRACT, a.getAbstr());
                cv.put(Contract.TABLE_ARTICLES.COLUMN_NAME_PUBLISHED_DATE, a.getPublished_date());
                cv.put(Contract.TABLE_ARTICLES.COLUMN_NAME_THUMBURL, a.getThumbUrl());
                cv.put(Contract.TABLE_ARTICLES.COLUMN_NAME_URL, a.getUrl());
                db.insert(Contract.TABLE_ARTICLES.TABLE_NAME, null, cv);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    /**
     *
     * @param db: reference to the database created using SQLiteDatabase
     *          This method deletes all the columns from the table.
     */
    public static void deleteAll(SQLiteDatabase db) {
        db.delete(Contract.TABLE_ARTICLES.TABLE_NAME, null, null);
    }

}
