package com.example.supernotepad;

//import class
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import androidx.appcompat.widget.SwitchCompat;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//NoteBode class
public class NoteBody extends AppCompatActivity {

    public static File currentFile;
    public static Note loadedNote;
    public static int textSize = 25;
    //Statement of variables
    Button backButton;
    SwitchCompat toggleCheckbox;
    EditText noteTitle;
    EditText noteBody;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_note_body);
        initElements();//initializing elements
        loadNote();
        setNoteBodySize(); // define the text size of notebody
    }

    //Initialization an object of action
    public void initElements(){

        //button initialization.
        backButton = (Button)findViewById(R.id.button_back);
        saveButton= (Button)findViewById(R.id.layout_note_save);

        //switch initialization.
        toggleCheckbox = (SwitchCompat)findViewById(R.id.switch_toggle_checkbox);

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
               navigateToMainActivity();
                loadedNote =null;
            }
        };
        //create click listener for toggleCheckbox
        View.OnClickListener toggleCheckboxListener = new View.OnClickListener(){
            public void onClick(View V){
                //implementation
            }
        };
        //create click listener for saveButton
        View.OnClickListener saveButtonListener=new View.OnClickListener(){
            public void onClick(View V){
                //calling the saveNote function
                saveNote();
            }
        };

        //setting listener to backButton to "backButtonListener"
        backButton.setOnClickListener(backButtonListener);

        //setting listen to toggleCheckbox to "toggleCheckboxListener"
        toggleCheckbox.setOnClickListener(toggleCheckboxListener);

        //setting listener to saveButton to "saveButtonListener"
        saveButton.setOnClickListener(saveButtonListener);
    }
    //save note function
    public void saveNote() {
        //creating filename
        //the getText command brings the text in bytes so we need to add the toString command
        String fileName = noteTitle.getText().toString();

        //turning the note text to string
        String noteText = noteBody.getText().toString();

        //creating a reference to the file
        //FileOutputStream is a library who create or if already created open the wanted file

        FileOutputStream currentFile = null;
        try  {

            //searching the file by name and giving only our app access to the file

            currentFile = openFileOutput(fileName,MODE_PRIVATE);
            //turning the noteText string into bytes
            currentFile.write(noteText.getBytes());


            //a message to the user where the current file saved.
            Toast.makeText(this, "save to "+getFilesDir()+"/"+fileName,Toast.LENGTH_LONG).show();
        }//end try

        //for the openFile command - if file is not found.
        catch (FileNotFoundException e) {
            Toast.makeText(this,"no file!", Toast.LENGTH_SHORT);
            e.printStackTrace();

        }
        //for the write command- if there was a problem turning the string into bytes
        catch (IOException f) {
            Toast.makeText(this,"IO!", Toast.LENGTH_SHORT);
            f.printStackTrace();

        }
            catch(Exception a)
                {
                    Toast.makeText(this,"general!", Toast.LENGTH_SHORT);
                }

        finally {
            //if file was found
            if(currentFile !=null){
                try {
                    //to close the current file
                    currentFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }//end finally
    }//end saveNote function

//    public static void loadNote(File note){
//        //set file name to title.
//
//        //read file using objects and the text
//        //to the note body.
//
//        //Declare all members of the map population.
//        FileInputStream fis=null;
//        InputStreamReader isr= null;
//        BufferedReader br= null;
//
//        }
        private void navigateToMainActivity()
            {
                Intent intent =new Intent(NoteBody.this, MainActivity.class);
                startActivity(intent);
            }

       public void setNoteBodySize(){
        noteBody.setTextSize(textSize);
    }

        @Override
        public void onBackPressed()
        {
            super.onBackPressed();
            navigateToMainActivity();
        }

        private void loadNote()
            {
                if (loadedNote ==null)
                    return;

                noteTitle.setText(loadedNote.title);
                noteBody.setText(loadedNote.body);
            }


}