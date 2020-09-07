package com.example.supernotepad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class NoteBody extends AppCompatActivity {
    Button backButton;
    Switch toggleCheckbox;
    EditText noteTitle;
    EditText noteBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initElements();
    }
    public void initElements(){
        backButton = (Button)findViewById(R.id.button_back);
        toggleCheckbox = (Switch)findViewById(R.id.switch_toggle_checkbox);
        noteTitle = (EditText)findViewById(R.id.layout_note_title);
        noteBody = (EditText)findViewById(R.id.layout_note_body);
    }

}