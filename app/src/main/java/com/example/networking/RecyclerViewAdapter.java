package com.example.networking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Plant> Mountains;
    private LayoutInflater layoutInflater;
    private OnClickListener onClickListener;

    //Constructor
    RecyclerViewAdapter(Context context, ArrayList<Plant> Mountains, OnClickListener onClickListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.Mountains = Mountains;
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
        holder.title.setText(Mountains.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return Mountains.size();
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
            onClickListener.onClick(Mountains.get(getAdapterPosition()));
        }
    }

    public interface OnClickListener {
        void onClick(Plant Mountain);
    }

    public void newData(ArrayList<Plant> Mountains)
    {
        this.Mountains=Mountains;
    }
}