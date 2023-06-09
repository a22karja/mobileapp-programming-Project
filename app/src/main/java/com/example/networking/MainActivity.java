package com.example.networking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=a22karja";
    public ArrayList<Plant> Plants =new ArrayList<Plant>();//arraylist that holds information

    RecyclerViewAdapter adapter;

    Calendar calender;
    SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
    String Time;

    private SharedPreferences myPreferenceRef;
    private SharedPreferences.Editor myPreferenceEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        myPreferenceRef = getPreferences(MODE_PRIVATE);
        myPreferenceEditor = myPreferenceRef.edit();
        //Check where to get information from
        if(Plants.size()==0){
            if(myPreferenceRef.getString("MyAppPreferenceArray", "No preference found.").equals("No preference found."))
            {
                new JsonTask(this).execute(JSON_URL);
            }
            else
            {
                Gson gson=new Gson();
                String json=myPreferenceRef.getString("MyAppPreferenceArray", "No preference found.");
                Type type=new TypeToken<ArrayList<Plant>>(){}.getType();
                Plants=gson.fromJson(json,type);
            }
        }




        //Initialize adapter so that it isnt null and is connectet to the ArrayList Plants
        adapter = new RecyclerViewAdapter(this, Plants, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(Plant item) {

                Intent i = new Intent(MainActivity.this, MoreInformation.class);
                i.putExtra("KEY_NAME", item);
                startActivityForResult(i,1);

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
        Type type = new TypeToken<ArrayList<Plant>>() {}.getType();
        //Fetches the data in the string json so that it can be put into the ArrayList Plants
        Plants=gson.fromJson(json, type);

        //Updates the adapter data
        adapter.newData(Plants);
        adapter.notifyDataSetChanged();
    }

    //Go to about activity
    public void goAbout(View v)
    {
        Intent i = new Intent(MainActivity.this,ABOUT.class);
        startActivityForResult(i,2);
    }

    //Handle information from child aktivitys
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle extras = getIntent().getExtras();
        //Handle information from plant information page, here the data get updated incase someone presses that they waterd the plant
        if(requestCode==1)
        {
            if(resultCode==RESULT_OK)
            {
                String plantID=data.getStringExtra("KEY_NAME2");
                for (Plant p : Plants) {
                    if (p.getID().equals(plantID)) {
                        calender = Calendar.getInstance();
                        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        Time = simpleDateFormat.format(calender.getTime());
                        p.setCompany(Time);

                        myPreferenceEditor.clear();
                        Gson gson=new Gson();
                        String Pson=gson.toJson(Plants);
                        myPreferenceEditor.putString("MyAppPreferenceArray",Pson);
                        myPreferenceEditor.apply();

                        RecyclerView view = findViewById(R.id.view);
                        view.setLayoutManager(new LinearLayoutManager(this));
                        view.setAdapter(adapter);

                        break;
                    }
                }
            }
        }
        //Clear data if the user pressed the red button in ABOUT activity
        else if (requestCode==2) {
            if(resultCode==RESULT_OK)
            {
                myPreferenceEditor.clear();
                myPreferenceEditor.apply();
                finish();
                startActivity(getIntent());
            }
        }
    }


}
