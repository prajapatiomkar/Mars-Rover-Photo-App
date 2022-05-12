package com.example.project1;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.TooManyListenersException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class QueryUtils {


    public static ArrayList<Word> fetchMarsData(String url) {
        String str=getData(url);
        ArrayList<Word> words = extractData(str);
        return words;
    }

    private static ArrayList<Word> extractData(String response) {
        ArrayList<Word> words = new ArrayList<>();
        try{
            JSONObject baseObj =new JSONObject(response);
            JSONArray photosArr = baseObj.getJSONArray("photos");
            for (int i=0;i<photosArr.length();i++){
                JSONObject currPhotoArr = photosArr.getJSONObject(i);
                JSONObject cameraObj = currPhotoArr.getJSONObject("camera");
                String fullName = cameraObj.getString("full_name");
                String earthDate = currPhotoArr.getString("earth_date");
                String imgSrc = currPhotoArr.getString("img_src");

                Word word = new Word(earthDate,imgSrc,fullName);
                words.add(word);
                Log.d("TEst", imgSrc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }return words;
    }

    private static String getData(String urls) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urls)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
