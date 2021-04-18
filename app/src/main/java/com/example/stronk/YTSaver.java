package com.example.stronk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;


public class YTSaver extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    RecyclerView recyclerView;
    YoutubeAdapter adapter;
    Button mButton;
    EditText mEdit;
    ImageButton homeButton;
    String url, saveName;
    Map<String, ?> allEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_y_t_saver);
        getSupportActionBar().hide();
        sharedPreferences = this.getSharedPreferences("Links", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();


        allEntries = sharedPreferences.getAll();
        Vector<String> keys = new Vector<String>();
        final ArrayList<String> keysArray = new ArrayList<String>();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            keys.add(entry.getKey());
            keysArray.add(entry.getKey());
        }

        recyclerView = findViewById(R.id.List);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new YoutubeAdapter(this, keysArray);
        recyclerView.setAdapter(adapter);

        mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
           public void onClick(View view) {
                mEdit = (EditText)findViewById(R.id.editTextTextPersonName);
                url = mEdit.getText().toString();
                mEdit = (EditText)findViewById(R.id.SaveNameBox);
                saveName = mEdit.getText().toString();

                if (saveName.length() > 24)
                    Toast.makeText(getApplication(),"ERROR: Only 24 characters for save name", Toast.LENGTH_LONG).show();
                else {
                    if (URLUtil.isValidUrl(url)) {
                        keysArray.add(saveName);
                        editor.putString(saveName, url);
                        editor.apply();
                        adapter.notifyDataSetChanged();

                        Toast.makeText(getApplication(), saveName + " Saved.", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getApplication(), "ERROR: Not valid URL. Please try again", Toast.LENGTH_LONG).show();
                    }
                }

           }
        });

        homeButton = findViewById(R.id.homeButtonYT);
        homeButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(YTSaver.this, MainActivity.class));
           }
        });
    }
}