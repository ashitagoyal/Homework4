package com.example.ashitagoyal.homework4;

/**
 * Created by Ashita on 7/28/17.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.ashitagoyal.homework4.data.Article;
import com.example.ashitagoyal.homework4.data.DBHelper;
import com.example.ashitagoyal.homework4.data.DatabaseUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class RefreshTasks {

    public static final String ACTION_REFRESH = "refresh";

    /**
     *
     * @param context:It is the reference to abstract class Context which allows access to application specific resources and classes.
     *
     */
    public static void refreshArticles(Context context) {
        ArrayList<Article> result = null;
        URL url = NetworkUtils.makeURL();

        SQLiteDatabase db = new DBHelper(context).getWritableDatabase();

        try {
            DatabaseUtils.deleteAll(db);
            String json = NetworkUtils.getResponseFromHttpUrl(url);
            result = NetworkUtils.parseJSON(json);
            DatabaseUtils.bulkInsert(db, result);

        } catch (IOException e) {
            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        db.close();
    }
}