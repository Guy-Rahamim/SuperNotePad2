package com.example.supernotepad;
//import class
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;

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
        ImageButton mainButtonTools;
        ImageButton mainButtonSearchOpen;
        Button mainButtonAddNote;
        AutoCompleteTextView mainButtonSearch;

        @Override
        protected void onCreate(Bundle savedInstanceState)
            {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                initElements();//initializing elements
            }

        //Initialization an object of action
        public void initElements()
            {

                //imageButton initialization.
                mainButtonTools = (ImageButton) findViewById(R.id.main_button_tools);
                mainButtonSearchOpen = (ImageButton) findViewById(R.id.main_button_search_open);

                //button initialization.
                mainButtonAddNote = (Button) findViewById(R.id.main_button_add_note);

                //AutoCompleteTextView initialization.
                mainButtonSearch = (AutoCompleteTextView) findViewById(R.id.main_button_search);

                initClickListeners();//initializing Click Listeners
            }

        //initializing Click Listeners
        public void initClickListeners()
            {

                //create click listener for mainButtonTools
                View.OnClickListener mainButtonToolsListener = new View.OnClickListener()
                    {
                        public void onClick(View V)
                            { //implementation}
                                launchActivitySettings();//launch Activity Settings
                            }
                    };

                //create click listener for mainButtonSearchOpen
                View.OnClickListener mainButtonSearchOpenListener = new View.OnClickListener()
                    {
                        public void onClick(View V)
                            {//implementation
                            }
                    };

                //create click listener for mainButtonAddNote
                View.OnClickListener mainButtonAddNoteListener = new View.OnClickListener() {
                        public void onClick(View V)
                            {
                                //implementation
                                // launchActivityNoteBody();
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


        //launch Activity Note Body
        // private void launchActivityNoteBody() {

        //Called only if permission is given.

        @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        public void launchActivityNoteBody()
            {

                Intent intentNoteBody = new Intent(this, NoteBody.class);
                startActivity(intentNoteBody);
            }


        //******************Handaling Storage Permission*****************//

        //Called after permission is given or denied.
        //It acts according to grantResults.

        //launch activity

        //on rationale
        @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        void showRationaleForStorage(final PermissionRequest request)
            {

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
        void onPermissionDenied()
            {
                AlertDialog.Builder deniedDialog = new AlertDialog.Builder(this);
                deniedDialog
                        .setTitle("Permission Required")
                        .setMessage("You will not be able to use the app without storage permission.")
                        .show();
            }

        @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        void onNeverAskAgain()
            {
                Toast.makeText(this, "Never asked again", Toast.LENGTH_SHORT).show();
            }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
            {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
            }

        private void launchActivitySettings()
            {
                Intent intentSettings = new Intent(this, Settings.class);
                startActivity(intentSettings);
            }
    }
