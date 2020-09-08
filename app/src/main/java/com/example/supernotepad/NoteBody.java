package com.example.supernotepad;

//import class
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

//NoteBode class
public class NoteBody extends AppCompatActivity {

    //Statement of variables
    Button backButton;
    Switch toggleCheckbox;
    EditText noteTitle;
    EditText noteBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initElements();//initializing elements
    }

    //Initialization an object of action
    public void initElements(){

        //button initialization.
        backButton = (Button)findViewById(R.id.button_back);

        //switch initialization.
        toggleCheckbox = (Switch)findViewById(R.id.switch_toggle_checkbox);

        //edit text initialization.
        noteTitle = (EditText)findViewById(R.id.layout_note_title);
        noteBody = (EditText)findViewById(R.id.layout_note_body);

        initClickListeners();//initializing Click Listeners
    }

    //initializing Click Listeners
    public void initClickListeners(){

        //create click listener for backButton
        View.OnClickListener backButtonListener = new View.OnClickListener(){
            public void onClick(View V){
          //implementation
            }
        };
        //create click listener for toggleCheckbox
        View.OnClickListener toggleCheckboxListener = new View.OnClickListener(){
            public void onClick(View V){
                //implementation
            }
        };

        //setting listener to backButton to "backButtonListener"
        backButton.setOnClickListener(backButtonListener);
        //setting listen to toggleCheckbox to "toggleCheckboxListener"
        toggleCheckbox.setOnClickListener(toggleCheckboxListener);
    }
}