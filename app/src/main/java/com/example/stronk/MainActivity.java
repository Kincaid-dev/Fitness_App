package com.example.stronk;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.stronk.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ColorStateList def;
    TextView item1;
    TextView item2;
    TextView item3;
    TextView item4;
    TextView select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        item3.setOnClickListener(this);
        item4.setOnClickListener(this);
        select = findViewById(R.id.select);
        def = item2.getTextColors();
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.item1){
            select.animate().x(0).setDuration(100);
            item1.setTextColor(Color.WHITE);
            item2.setTextColor(def);
            item3.setTextColor(def);
            item4.setTextColor(def);
        } else if (view.getId() == R.id.item2){
            startActivity(new Intent(MainActivity.this, YTSaver.class));
            item1.setTextColor(def);
            item2.setTextColor(Color.WHITE);
            item3.setTextColor(def);
            item4.setTextColor(def);
            int size = item2.getWidth();
            select.animate().x(size).setDuration(100);
        } else if (view.getId() == R.id.item3){
            startActivity(new Intent(MainActivity.this, CalorieCounter.class));
            item1.setTextColor(def);
            item3.setTextColor(Color.WHITE);
            item2.setTextColor(def);
            item4.setTextColor(def);
            int size = item2.getWidth() * 2;
            select.animate().x(size).setDuration(100);
        } else if (view.getId() == R.id.item4){
            item1.setTextColor(def);
            item4.setTextColor(Color.WHITE);
            item2.setTextColor(def);
            item3.setTextColor(def);
            int size = item2.getWidth() * 2;
            select.animate().x(size).setDuration(100);
        }
    }
}