package com.example.networking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
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
    public ArrayList<Plant> Plants =new ArrayList<Plant>();//arraylist that holds information

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

        //Initialize adapter so that it isnt null and is connectet to the ArrayList Plants
        adapter = new RecyclerViewAdapter(this, Plants, new RecyclerViewAdapter.OnClickListener() {


            @Override
            public void onClick(Plant item) {
                Toast.makeText(MainActivity.this, item.getID(), Toast.LENGTH_SHORT).show();
                Log.d("BACON",Plants.get(1).toString());
            }
        });

        //Sets the recyclerView with ID view so it is connectet to adapter
        RecyclerView view = findViewById(R.id.view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);
        //Toast.makeText(MainActivity.this, Time, Toast.LENGTH_SHORT).show();
        //Toast.makeText(MainActivity.this, String.valueOf(adapter.getItemCount()), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPostExecute(String json) {

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Plant>>() {}.getType();
        //Fetches the data in the string json so that it can be put into the ArrayList Plants
        Plants=gson.fromJson(json, type);
        Log.d("MainActivityB", Plants.toString());
        //Updates the adapter data
        adapter.newData(Plants);
        adapter.notifyDataSetChanged();
    }

    public void goAbout(View v)
    {
        Intent intent = new Intent(MainActivity.this,ABOUT.class);
        startActivity(intent);
    }

}
