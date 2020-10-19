package com.example.supernotepad;

import android.app.Activity;
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
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Note extends Activity
    {
        // ************** TO DO - REMOVE BUTTON FROM CLASS *****************//

        File file;
        Button button;
        String title;
        String body;
        Context context;


        //******MAKE THIS CONSTRUCTOR OBSOLETE************//
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
        //******MAKE THIS CONSTRUCTOR OBSOLETE************//


        public Note()  {

        }


        //******MAKE THIS CONSTRUCTOR OBSOLETE************//
        public Note(File file, Button button) {
            this.file= file;
            this.button = button;
            initTitleAndBody();
        }
        //******MAKE THIS CONSTRUCTOR OBSOLETE************//


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

        void saveNote(String title, String body, Context context) {
            this.body=body;
            this.title=title;

            FileWriter fw=null;
            PrintWriter pw = null;
            try{
            //    File file= new File(context.getFilesDir()+"/"+title+".txt");
                File file= new File(context.getFilesDir(),title+".txt");

                   if (file.createNewFile()) {

                    fw= new FileWriter(file);
                    pw= new PrintWriter(fw);
                    pw.println(this.body);

                    pw.close();
                    fw.close();
                }

                   }
                    catch(IOException e) {
                        Toast.makeText(context,file.isFile() + " " + file.getAbsolutePath() , Toast.LENGTH_SHORT).show();
                        }
                    catch(NullPointerException i)
                        {

                            if (file==null)
                                    Toast.makeText(context, "file is null pointer",Toast.LENGTH_SHORT).show();

                            if (fw==null)
                            Toast.makeText(context, "fw is null pointer",Toast.LENGTH_SHORT).show();

                            else if (pw==null)
                                Toast.makeText(context, "pw is null pointer",Toast.LENGTH_SHORT).show();
                            i.printStackTrace();
                        }
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
