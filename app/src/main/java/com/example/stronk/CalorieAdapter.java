package com.example.stronk;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class CalorieAdapter extends RecyclerView.Adapter<CalorieAdapter.ViewHolder>{
    Context context;
    ArrayList<String> keys;
    Map<String, ?> allEntries;

    public CalorieAdapter(Context context, ArrayList<String> keys) {
        this.context = context;
        this.keys = keys;
    }

    @Override
    public CalorieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.link_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CalorieAdapter.ViewHolder holder, int position) {
        SharedPreferences sp = context.getSharedPreferences("Calories", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();
        allEntries = sp.getAll();

        holder.mealText.setText(keys.get(position));
        holder.calText.setText((String)allEntries.get(keys.get(position)));
    }

    @Override
    public int getItemCount() {
        return keys.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mealText;
        TextView calText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealText = itemView.findViewById(R.id.MealName);
            calText = itemView.findViewById(R.id.CalorieText);
        }
    }
}
