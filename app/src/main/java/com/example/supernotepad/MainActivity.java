package com.example.supernotepad;
//import class
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.LinkedList;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;



@RuntimePermissions
public class MainActivity extends AppCompatActivity
    {
        //Statement of variables
        public LinkedList<File> fileList;
        ImageButton mainButtonTools;
        ImageButton mainButtonSearchOpen;
        Button mainButtonAddNote;
        AutoCompleteTextView mainButtonSearch;
        LinearLayout linLayout;
        ScrollView scrollLayout;
       public static SharedPreferences sharedPreferences;
        public static final String textSizeKey = "TEXT_SIZE";

        @Override
        protected void onCreate(Bundle savedInstanceState)
            {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);//create or reload the setting file.
                getSupportActionBar().hide();

                initElements();//initializing elements

                populateList();
               displayList();

            }

        //Initialization an object of action
        public void initElements()
            {
                fileList= new LinkedList<File>();

                //imageButton initialization.
                mainButtonTools = (ImageButton) findViewById(R.id.main_button_tools);
                mainButtonSearchOpen = (ImageButton) findViewById(R.id.main_button_search_open);

                //button initialization.
                mainButtonAddNote = (Button) findViewById(R.id.main_button_add_note);

                //AutoCompleteTextView initialization.
                mainButtonSearch = (AutoCompleteTextView) findViewById(R.id.main_button_search);


                //LinearLayout initialization
                linLayout=  (LinearLayout) findViewById(R.id.main_view_linearLayout);
               // linLayout.setOrientation(LinearLayout.VERTICAL);



                initClickListeners();//initializing Click Listeners
            }

        //initializing Click Listeners
        public void initClickListeners()
            {

                //create click listener for mainButtonTools
                View.OnClickListener mainButtonToolsListener = new View.OnClickListener()
                    {
                        public void onClick(View V) {
                                launchActivitySettings();//launch Activity Settings
                            }
                    };

                //create click listener for mainButtonSearchOpen
                View.OnClickListener mainButtonSearchOpenListener = new View.OnClickListener()
                    {
                        public void onClick(View V) {
                            }
                    };

                //create click listener for mainButtonAddNote
                View.OnClickListener mainButtonAddNoteListener = new View.OnClickListener() {
                        public void onClick(View V) {
                         MainActivityPermissionsDispatcher.launchActivityNoteBodyWithPermissionCheck(MainActivity.this);
                        }
                    };


                //setting listener to mainButtonTools to "mainButtonToolsListener"
                mainButtonTools.setOnClickListener(mainButtonToolsListener);
                //setting listener to mainButtonSearchOpen to "mainButtonSearchOpenListener
                mainButtonSearchOpen.setOnClickListener(mainButtonSearchOpenListener);
                //setting listener to mainButtonAddNote TO " mainButtonAddNoteListener"
                mainButtonAddNote.setOnClickListener(mainButtonAddNoteListener);
            }

        //Called only if permission is given.
        @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        public void launchActivityNoteBody() {
                Intent intentNoteBody = new Intent(this, NoteBody.class);
                startActivity(intentNoteBody);
                finish();
            }

        private void launchActivitySettings() {
                Intent intentSettings = new Intent(this, Settings.class);
                startActivity(intentSettings);
            }

        public void populateList() {
                File[] files= null;
                //Instantiate a new file and set its path
                //to the files directory.
                File dir= getFilesDir();

                //if file is a directory,
                //create a file array and instantiate it using File.listFiles().
                if (dir.isDirectory()) {
                        files= dir.listFiles();

                        //iterate over files and add them
                        //all to an array list.
                        for (File file: files) {
                                fileList.add(file);
                            }
                    }
            }

        public void displayList() {
                TextView tv;
                int currentColor =ResourcesCompat.getColor(getResources(),R.color.noteTextColor, null);

                //instantiate text view.
                //set text view layout params to match parent,wrap content
                //set the text view text.
                //set other attributes.
                //add text view to linear layout.

                //remove all views that exists currently on linLayout.
                linLayout.removeAllViews();

                //Instantiate TextView Params.
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                                           ViewGroup.LayoutParams.MATCH_PARENT,
                                          ViewGroup.LayoutParams.WRAP_CONTENT );

                        //iterate through file list, instantiate a new
                        //text view, set its params and add it to linLayout
                        for (final File file : fileList) {
                                final Note note = new Note(this,file);
                                note.setButtonAttributes(currentColor,file.getName());

                        //*******************FIND A BETTER LOCATION FOR CLICK LISTENER*****************//
                            note.button.setOnClickListener(new View.OnClickListener()
                    {
                        public void onClick(View v)
                            {
                                NoteBody.loadedNote =note;
                                moveToNoteBody();
                        //*******************FIND A BETTER LOCATION FOR CLICK LISTENER*****************//
                            }
                    });

                        linLayout.addView(note.button);
                        }
            }

        void moveToNoteBody() {
                Intent intent = new Intent(this,NoteBody.class);
                startActivity(intent);
                finish();
            }


        //******************Handaling Storage Permission*****************//

        //Called after permission is given or denied.
        //It acts according to grantResults.

        //launch activity

        //on rationale
        @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        void showRationaleForStorage(final PermissionRequest request) {

                //Instantiating buttons for the alert dialog.
                DialogInterface.OnClickListener onOK = new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                            {
                                request.proceed();
                            }
                    };

                DialogInterface.OnClickListener onCancel = new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                            {
                                request.cancel();
                            }
                    };


                //Instantiating alert dialog with alert buttons.
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Permission Required")
                        .setMessage("Storage access is required to create new notes.")
                        .setPositiveButton("Okay", onOK)
                        .setNegativeButton("Cancel", onCancel);

                //Displaying alert dialog.
                alert.show();

            }

        //on denied
        @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        void onPermissionDenied() {
                AlertDialog.Builder deniedDialog = new AlertDialog.Builder(this);
                deniedDialog
                        .setTitle("Permission Required")
                        .setMessage("You will not be able to use the app without storage permission.")
                        .show();
            }

        @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        void onNeverAskAgain() {
                Toast.makeText(this, "Never asked again", Toast.LENGTH_SHORT).show();
            }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
            }

    }
