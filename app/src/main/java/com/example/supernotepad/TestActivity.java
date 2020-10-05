package com.example.supernotepad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.generateViewId;

public class TestActivity extends AppCompatActivity
    {
        ArrayList<TextView> list;
        TextView newText;
        LinearLayout layout;
        Button button;
        public static int margin = 30;
        @Override
        protected void onCreate(Bundle savedInstanceState)
            {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_test);
                initElements();
                initializeList();
            }

        void initElements()
            {

                list= new ArrayList<TextView>();
                newText= new TextView(this);
                layout =(LinearLayout) findViewById(R.id.constraint);
                layout.setOrientation(LinearLayout.VERTICAL );
                button = (Button) findViewById(R.id.button);

                button.setOnClickListener(new View.OnClickListener()
                    {
                        public void onClick( View v)
                            {
                                addView();
                            }
                    });

            }

        void addView()
            {

                TextView view = new TextView(this);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
//                view.setLayoutParams(new ViewGroup.LayoutParams(200,200));

                view.setLayoutParams(params);
                view.setText("TEXT!!!");

                view.setTextColor(Color.BLACK);
                view.setTextSize(20);
                layout.addView(view);
            }

        void initializeList()
            {
                TextView tv= new TextView(this);
                tv.setLayoutParams(new ViewGroup.LayoutParams(200,400));
                tv.setText("1");
                list.add(tv);

                tv.setText("2");
                list.add(tv);

                tv.setText("3");
                list.add(tv);

                tv.setText("4");
                list.add(tv);

                tv.setText("5");
                list.add(tv);
            }
    }