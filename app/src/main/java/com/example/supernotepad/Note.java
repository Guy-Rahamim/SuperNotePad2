package com.example.supernotepad;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Note extends AppCompatActivity
    {
        File file;
        Button button;
        String title;
        String body;
        Context context;

        public Note(Context context, File file) {
                this.button= new Button(context);
                button.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                this.file=file;
                this.context=context;
                initTitleAndBody();
            }

        public Note(File file, Button button) {
            this.file= file;
            this.button = button;
            initTitleAndBody();
        }

        public void setButton(Button button) {
            this.button= ((this.button=button) !=null)? button:null;
        }

        public void setFile(File file) {
            this.file = (this.file=file) !=null ? file:null;
        }

        public void addClickListener(View.OnClickListener listener) {
            button.setOnClickListener(listener);
        }

        public void setButtonAttributes(int color, String name)
            {
                button.setTextSize(NoteBody.textSize);
                button.setTextColor(color);
                button.setText(name);


            }

        void moveToNoteBody()
            {
                Intent intent = new Intent(context,NoteBody.class);
                startActivity(intent);
            }


        private void initTitleAndBody()
            {
                try{
                    Scanner reader = new Scanner(file);
                    while (reader.hasNextLine()) {
                            body+=reader.nextLine();
                        }
                   }

                    catch(IOException e) {
                }
                title = file.getName();
            }
    }
