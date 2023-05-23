package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MoreInformation extends AppCompatActivity {

    Plant plant;


    Date c = Calendar.getInstance().getTime();
    Date latestWater;
    Date today;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_information);

        TextView Name=findViewById(R.id.name);
        TextView Company=findViewById(R.id.Company);
        TextView Location=findViewById(R.id.location);
        TextView Category=findViewById(R.id.Category);
        TextView Cost=findViewById(R.id.Cost);
        TextView MSG=findViewById(R.id.MSG);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            plant = (Plant) getIntent().getSerializableExtra("KEY_NAME");

            try {
                latestWater=sdf.parse(plant.getCompany());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            Calendar Kalender = Calendar.getInstance();
            Kalender.setTime(latestWater);
            Kalender.add(Calendar.DATE, plant.getCost());
            latestWater = Kalender.getTime();

            sdf.format(c);
            if (latestWater.after(c)) {
                MSG.setText("You dont need to water this plant");
            }
            else
            {
                MSG.setText("water the plant");
            }

            Name.setText(plant.getName());
            Company.setText(plant.getCompany());
            Location.setText(plant.getLocation());
            Category.setText(plant.getCategory());
            Cost.setText(String.valueOf(plant.getCost()));



            //The key argument here must match that used in the other activity
        }

    }
    public void goWaterBack(View v)
    {
        Intent i = new Intent(MoreInformation.this,MainActivity.class);
        i.putExtra("KEY_NAME2", plant.getID());
        setResult(RESULT_OK, i);
        finish();
    }
}