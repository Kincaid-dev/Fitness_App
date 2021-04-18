package com.example.stronk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.ViewHolder> {

    private ArrayList<String> keys;
    private final Context context;
    Map<String, ?> allEntries;
//    private static int TYPE_YT = 1;
//    private static int TYPE_CAL = 2;

    public YoutubeAdapter(Context context, ArrayList<String> keys) {
        this.context = context;
        this.keys = keys;
    }

    @NonNull
    @Override
    public YoutubeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
//        if (viewType == TYPE_YT) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.linkcard, parent, false);
            return new ViewHolder(view);
//        }
    }

    @Override
    public void onBindViewHolder(YoutubeAdapter.ViewHolder holder, final int position) {
//        if (getItemViewType(position) == TYPE_YT) {
            SharedPreferences sp = context.getSharedPreferences("Links", MODE_PRIVATE);
            final SharedPreferences.Editor editor = sp.edit();
            allEntries = sp.getAll();


            holder.textView.setText(keys.get(position));
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = (String) allEntries.get(keys.get(position));
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    Toast.makeText(context, "Clicked on " + url, Toast.LENGTH_SHORT).show();
                }
            });

            holder.delButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.remove(keys.get(position));
                    keys.remove(position);
                    allEntries.remove(position);
                    editor.apply();
                    notifyDataSetChanged();
                }
            });
//        }
//        else {
//            SharedPreferences sp = context.getSharedPreferences("Calories", MODE_PRIVATE);
//            final SharedPreferences.Editor editor = sp.edit();
//            allEntries = sp.getAll();
//
//            ((CalViewHolder)holder).mealText.setText(keys.get(position));
//            ((CalViewHolder)holder).calText.setText((String)allEntries.get(keys.get(position)));
//        }
    }

    @Override
    public int getItemCount() {
        return keys.size();
    }

//    @Override
//    public int getItemViewType(int position) {
//        if (URLUtil.isValidUrl((String)allEntries.get(keys.get(position)))) {
//            return TYPE_YT;
//        }
//        else
//            return TYPE_CAL;
//    }

//    public static class CalViewHolder extends RecyclerView.ViewHolder {
//        TextView mealText;
//        TextView calText;
//
//        CalViewHolder(@NonNull View itemView) {
//            super(itemView);
//            mealText = itemView.findViewById(R.id.Meal);
//            calText = itemView.findViewById(R.id.Calorie);
//        }
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        Button delButton;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.contact_name);
            delButton = itemView.findViewById(R.id.button3);
        }
    }
}
