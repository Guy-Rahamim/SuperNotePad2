package com.example.supernotepad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class settings extends AppCompatActivity {

    Button buttonBack;
    TextView settingsTitle;
    Switch switchDarkMode;
    Switch switchEnterSave;
    TextView textViewSortBy;
    Button buttonSortByChoice;
    TextView textViewTextSize;
    Button buttonTextSizeChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initElements();
    }

    public void initElements(){
        buttonBack = (Button)findViewById(R.id.button_back2);
        settingsTitle = (TextView)findViewById(R.id.textView_settings_title);
        switchDarkMode = (Switch)findViewById(R.id.switch_darkMode);
        switchEnterSave = (Switch)findViewById(R.id.switch_enterSave);
        textViewSortBy=(TextView)findViewById(R.id.textView_sortBy);
        buttonSortByChoice = (Button)findViewById(R.id.button_sortByChoice);
        textViewTextSize = (TextView)findViewById(R.id.textView_textSize);
        buttonTextSizeChoice = (Button)findViewById(R.id.button_textSizeChoice);
    }
}