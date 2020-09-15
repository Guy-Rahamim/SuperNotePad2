package com.example.supernotepad;
//import class
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
        //Statement of variables
        ImageButton mainButtonTools;
        ImageButton mainButtonSearchOpen;
        Button mainButtonAddNote;
        AutoCompleteTextView mainButtonSearch;

        @Override
    protected void onCreate(Bundle savedInstanceState){
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                initElements();//initializing elements
    }

        //Initialization an object of action
    public void initElements(){

            //imageButton initialization.
            mainButtonTools = (ImageButton)findViewById(R.id.main_button_tools);
            mainButtonSearchOpen = (ImageButton)findViewById(R.id.main_button_search_open);

            //button initialization.
            mainButtonAddNote = (Button)findViewById(R.id.main_button_add_note);
            initClickListeners();//initializing Click Listeners
            //AutoCompleteTextView initialization.
            mainButtonSearch = (AutoCompleteTextView)findViewById(R.id.main_button_search);
    }

        //initializing Click Listeners
    public void initClickListeners() {

            //create click listener for mainButtonTools
            View.OnClickListener mainButtonToolsListener = new View.OnClickListener(){
                public void onClick(View V){ //implementation}
                }
            };
            //create click listener for mainButtonSearchOpen
            View.OnClickListener mainButtonSearchOpenListener = new View.OnClickListener(){
                public void onClick(View V){//implementation
                }
            };
            //create click listener for mainButtonAddNote
            View.OnClickListener mainButtonAddNoteListener = new View.OnClickListener(){
                public void onClick(View V){//implementation}
                }
            };

            //setting listener to mainButtonTools to "mainButtonToolsListener"
            mainButtonTools.setOnClickListener(mainButtonToolsListener);
            //setting listener to mainButtonSearchOpen to "mainButtonSearchOpenListener
            mainButtonSearchOpen.setOnClickListener(mainButtonSearchOpenListener);
            //setting listener to mainButtonAddNote TO " mainButtonAddNoteListener"
            mainButtonAddNote.setOnClickListener(mainButtonAddNoteListener);
    }
}