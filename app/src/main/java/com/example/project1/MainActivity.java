package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static String URL_STRING = "";
    DemoAdapter demoAdapter;
    ProgressBar pb;
    Button searchBtn;
    Spinner spinnerView;
    String solDayNumber = "";
    String camNameValue = "";
    NumberPicker solDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ListView listView = findViewById(R.id.list);
        spinnerView = findViewById(R.id.spinnerView);
        searchBtn = findViewById(R.id.searchBtn);
        pb = findViewById(R.id.pb);
        pb.setVisibility(View.INVISIBLE);
        
        spinnerView.setOnItemSelectedListener(this);
        String[] camName = getResources().getStringArray(R.array.camName);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, camName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerView.setAdapter(adapter);


        solDay = findViewById(R.id.solDay);
        solDay.setMinValue(0);
        solDay.setMaxValue(1000);

        solDay.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                solDayNumber = String.valueOf(i1);
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb.setVisibility(View.VISIBLE);
                URL_STRING = String.format("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?api_key=DEMO_KEY&sol=%s&camera=%s", solDayNumber, camNameValue);
                DemoAsyncTask demoAsyncTask = new DemoAsyncTask();
                demoAsyncTask.execute(URL_STRING);
                demoAdapter = new DemoAdapter(getApplicationContext(), new ArrayList<Word>());
                listView.setAdapter(demoAdapter);

            }

        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.spinnerView) {
            camNameValue = adapterView.getItemAtPosition(i).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public class DemoAsyncTask extends AsyncTask<String, Void, ArrayList<Word>> {
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