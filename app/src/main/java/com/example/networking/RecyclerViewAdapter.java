package com.example.networking;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Plant> Plants;
    private LayoutInflater layoutInflater;
    private OnClickListener onClickListener;

    //Constructor
    RecyclerViewAdapter(Context context, ArrayList<Plant> Plants, OnClickListener onClickListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.Plants = Plants;
        this.onClickListener = onClickListener;
    }

    //Fills information into layout from item.xml
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item, parent, false));
    }

    //sets the text in title
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        //put in text
        holder.title.setText(Plants.get(position).getName()+" Location:"+Plants.get(position).getLocation());

        Date latestWater;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date c = Calendar.getInstance().getTime();
        //change color in case it needs watering
        try {
            latestWater=sdf.parse(Plants.get(position).getCompany());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Calendar Kalender = Calendar.getInstance();
        Kalender.setTime(latestWater);
        Kalender.add(Calendar.DATE, Plants.get(position).getCost());
        latestWater = Kalender.getTime();

        sdf.format(c);
        if (!latestWater.after(c)) {
            holder.title.setTextColor(Color.WHITE);
            holder.title.setBackgroundColor(ContextCompat.getColor(context,R.color.red));
        }

    }

    @Override
    public int getItemCount() {
        return Plants.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.title);
        }

        @Override
        public void onClick(View view) {
            onClickListener.onClick(Plants.get(getAdapterPosition()));
        }
    }

    public interface OnClickListener {
        void onClick(Plant Mountain);
    }

    public void newData(ArrayList<Plant> Mountains)
    {
        this.Plants =Mountains;
    }
}