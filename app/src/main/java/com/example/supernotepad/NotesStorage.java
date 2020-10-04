package com.example.supernotepad;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class NotesStorage extends AppCompatActivity
    {
        public static HashMap<String, String> notesMap;

        public static void addNote(String title,String note){
            notesMap.put(title,note);
        }

        public void instantiateMap()
            {
                //instantiate files, check if its a directory
                //if it is, create a file array and instantiate it.

                //create a FileInputStream.
                // instantiate a new InputStreamReader with the FileInputStream.
                //instantiate a new BufferedReader with the InputStreamReader.
                FileInputStream fileReader= null;
                InputStreamReader streamReader=null;
                BufferedReader bufferedReader = null;

                File dir = getFilesDir();

                if (dir.isDirectory()){
                    String noteBody="";
                    String currentLine="";

                        try{
                                File[] files= dir.listFiles();

                                for (File file:files){
                                    fileReader= new FileInputStream(file);
                                    streamReader = new InputStreamReader(fileReader);
                                    bufferedReader = new BufferedReader(streamReader);

                                    while ((currentLine = bufferedReader.readLine()) !=null){
                                        noteBody+=currentLine+"\n";
                                    }

                                    notesMap.put(file.getName(),noteBody);
                                }
                        }

                        catch(IOException e){

                        }


                    }


            }

        public static void displayMap(){

            }

        public static void deleteEntry(String key){

        }



    }
