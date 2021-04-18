package com.example.stronk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

public class CalorieCounter extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    RecyclerView recyclerView;
    CalorieAdapter adapter;
    Button saveButton;
    Button resetButton;
    ImageButton homeButton;
    EditText mealName;
    EditText calorieText;
    int totalCalories = 0;
    TextView totalCal;
    String mealNameString, CalString;
    Map<String, ?> allEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_counter);
        getSupportActionBar().hide();
        totalCal = (TextView)findViewById(R.id.CalorieCount);
        sharedPreferences = this.getSharedPreferences("Calories", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        allEntries = sharedPreferences.getAll();
        Vector<String> keys = new Vector<String>();
        final ArrayList<String> keysArray = new ArrayList<String>();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            keys.add(entry.getKey());
            keysArray.add(entry.getKey());
            totalCalories += Integer.parseInt((String)entry.getValue());
        }
        totalCal.setText(String.valueOf(totalCalories));

        recyclerView = findViewById(R.id.CalorieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CalorieAdapter(this, keysArray);
        recyclerView.setAdapter(adapter);

        saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mealName = (EditText)findViewById(R.id.Meal);
               mealNameString = mealName.getText().toString();
               calorieText = (EditText)findViewById(R.id.Calorie);
               CalString = calorieText.getText().toString();
               if (CalString.equals("") || Integer.parseInt(CalString) <= 0) {
                   Toast.makeText(getApplication(),"ERROR: Calories must be greater than 0", Toast.LENGTH_LONG).show();
               }
               else {
                   if (mealNameString.equals("")) {
                       Toast.makeText(getApplication(),"ERROR: Must input meal name", Toast.LENGTH_LONG).show();
                   }
                   else {
                       keysArray.add(mealNameString);
                       editor.putString(mealNameString, CalString);
                       editor.apply();
                       adapter.notifyDataSetChanged();

                       totalCalories += Integer.parseInt(CalString);
                       totalCal.setText(String.valueOf(totalCalories));
                   }
               }
           }
        });


        resetButton = (Button)findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               keysArray.clear();
               editor.clear();
               editor.apply();
               adapter.notifyDataSetChanged();
               totalCalories = 0;
               totalCal.setText("0");
           }
        });

        homeButton = findViewById(R.id.homeButtonCal);
        homeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(CalorieCounter.this, MainActivity.class));
                }
            });

    }
}