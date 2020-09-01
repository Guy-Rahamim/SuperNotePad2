package com.example.supernotepad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class NoteBody extends AppCompatActivity
    {
Button back;
EditText noteBody;
int changeLogPointer;
ArrayList<String> changeLog= new ArrayList<String>();

        @Override
        protected void onCreate(Bundle savedInstanceState)
            {
                super.onCreate(savedInstanceState);
                getSupportActionBar().hide();
                back=(Button) findViewById(R.id.button_back);
                noteBody=(EditText) findViewById(R.id.layout_note_body);
            }

        public void initClickListeners()
            {

                back.setOnClickListener(new View.OnClickListener()
                    {
                        public void onClick(View v)
                            {

                            }
                    });

                noteBody.addTextChangedListener(new TextWatcher()
                    {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after)
                            {

                            }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count)
                            {

                            }

                        @Override
                        public void afterTextChanged(Editable s)
                            {
                                changeLog.add(noteBody.getText().toString());
                            }
                    });
            }
    }