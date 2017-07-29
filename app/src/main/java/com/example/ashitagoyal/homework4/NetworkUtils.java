package com.example.ashitagoyal.homework4;

import android.net.Uri;
import android.util.Log;

import com.example.ashitagoyal.homework4.data.Article;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Ashita on 7/28/17.
 */

public class NetworkUtils {

    public static final String TAG = "NetworkUtils";

    final static String NEWS_BASE_URL = "https://newsapi.org/v1/articles";

    public static final String APIKEY_NEWS_API="6899a12b34ae4739ad5eba1f35a62e86";
    public static final String PARAM_SOURCE = "source";
    public static final String PARAM_SORTBY = "sortBy";
    public static final String PARAM_APIKEY= "apiKey";


    public static URL makeURL() {
        Uri uri = Uri.parse(NEWS_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_APIKEY,APIKEY_NEWS_API)
                .appendQueryParameter(PARAM_SOURCE,"the-next-web")
                .appendQueryParameter(PARAM_SORTBY,"latest")
                .build();

        URL url = null;
        try {
            String urlString = uri.toString();
            Log.d(TAG, "Url: " + urlString);
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**

     * @param url:it refers to the class URL which ia a pointer to the "resource" on the WWW.
     * @return: this method is either returning the url user looking for or is returning null if nothing is found.
     * @throws IOException
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner input = new Scanner(in);

            input.useDelimiter("\\A");
            String result = (input.hasNext()) ? input.next() : null;
            return result;

        }catch (IOException e){
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return null;
    }

    /**
     *
     * @param json: json is a String variable which is used to parse the data as shown in method
     * @return:parsed data in the form of arraylist.
     * @throws JSONException
     */

    public static ArrayList<Article> parseJSON(String json) throws JSONException {
        ArrayList<Article> result = new ArrayList<>();
        JSONObject main = new JSONObject(json);
        JSONArray items = main.getJSONArray("results");

        for(int i = 0; i < items.length(); i++){
            JSONObject item = items.getJSONObject(i);
            String title = item.getString("title");
            String publishedDate = item.getString("published_date");
            String abstr = item.getString("abstract");
            String url = item.getString("url");
            String imgUrl=item.getString("urlToImage");
            result.add(new Article(title, publishedDate, abstr, imgUrl, url));

        }
        Log.d(TAG, "final articles size: " + result.size());
        return result;
    }


}
