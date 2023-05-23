package com.example.networking;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=a22karja";
    public ArrayList<Mountain> Mountains =new ArrayList<Mountain>();//arraylist that holds information

    RecyclerViewAdapter adapter;

    Calendar calender;
    SimpleDateFormat simpleDateFormat;
    String Time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {
            // <---- run your one time code here
            //databaseSetup();

            // mark first time has ran.
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }

        //fetches json data from JSON_URL
        new JsonTask(this).execute(JSON_URL);

        calender=Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
        Time=simpleDateFormat.format(calender.getTime());

        //Initialize adapter so that it isnt null and is connectet to the ArrayList Mountains
        adapter = new RecyclerViewAdapter(this, Mountains, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(Mountain item) {
                Toast.makeText(MainActivity.this, Time, Toast.LENGTH_SHORT).show();
            }
        });

        //Sets the recyclerView with ID view so it is connectet to adapter
        RecyclerView view = findViewById(R.id.view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);
    }

    @Override
    public void onPostExecute(String json) {

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Mountain>>() {}.getType();
        //Fetches the data in the string json so that it can be put into the ArrayList Mountains
        Mountains=gson.fromJson(json, type);
        Log.d("MainActivityB", Mountains.toString());
        //Updates the adapter data
        adapter.newData(Mountains);
        adapter.notifyDataSetChanged();
    }

}
