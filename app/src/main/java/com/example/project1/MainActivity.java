package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String URL_STRING = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?api_key=DEMO_KEY&sol=1000";
    DemoAdapter demoAdapter;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.list);
        pb = findViewById(R.id.pb);
        pb.setVisibility(View.VISIBLE);
        DemoAsyncTask demoAsyncTask = new DemoAsyncTask();
        demoAsyncTask.execute(URL_STRING);
        demoAdapter = new DemoAdapter(getApplicationContext(), new ArrayList<Word>());
        listView.setAdapter(demoAdapter);


    }
    public class DemoAsyncTask extends AsyncTask<String,Void,ArrayList<Word>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Word> doInBackground(String... strings) {
            ArrayList<Word> words = QueryUtils.fetchMarsData(strings[0]);
            return words;
        }

        @Override
        protected void onPostExecute(ArrayList<Word> words) {
            super.onPostExecute(words);
            demoAdapter.addAll(words);
            pb.setVisibility(View.INVISIBLE);

        }
    }


}