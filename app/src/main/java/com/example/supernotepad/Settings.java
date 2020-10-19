package com.example.supernotepad;

//import class
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
//setting class
public class Settings extends AppCompatActivity {

    //Statement of variables
    Button buttonBack;
    Button buttonSortByChoice;
    Button buttonTextSizeChoice;
    Button buttonSmallTextSize;
    Button buttonMediumTextSize;
    Button buttonLargeTextSize;

    SwitchCompat switchDarkMode;
    SwitchCompat switchEnterSave;

    TextView settingsTitle;
    TextView textViewSortBy;
    TextView textViewTextSize;

    // define size for Text in NoteBody
    int smallSize = 10, mediumSize = 20, largeSize = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_settings);
        initElements();//Initializing elements
    }

    //Initializing elements
    public void initElements() {

        //button initialization.
        buttonBack = (Button) findViewById(R.id.button_back2);
        buttonSortByChoice = (Button) findViewById(R.id.button_sortByChoice);
        buttonTextSizeChoice = (Button) findViewById(R.id.button_textSizeChoice);
        buttonSmallTextSize = (Button) findViewById(R.id.button_textSmallSize);
        buttonMediumTextSize = (Button) findViewById(R.id.button_textMediumSize);
        buttonLargeTextSize = (Button) findViewById(R.id.button_textLargeSize);

        //switch initialization.
        switchDarkMode = (SwitchCompat) findViewById(R.id.switch_darkMode);
        switchEnterSave = (SwitchCompat) findViewById(R.id.switch_enterSave);

        //text view initialization.
        settingsTitle = (TextView) findViewById(R.id.textView_settings_title);
        textViewSortBy = (TextView) findViewById(R.id.textView_sortBy);
        textViewTextSize = (TextView) findViewById(R.id.textView_textSize);

        //Default invisibility of button text size view
        setInvisibilityTextSizeButton();

        //initializing click listener
        initClickListeners();






    }

    //initializing click listeners
    public void initClickListeners() {

        //create click listener for buttonBack
        View.OnClickListener buttonBackListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) { //implementation.
                finish();//Go back
            }
        };
        //create click listener for buttonSortByChoice
        View.OnClickListener buttonSortByChoiceListener = new View.OnClickListener(){
            public void onClick(View V) {//implementation
            }
        };

        //create click listener for buttonTextSizeChoice.
        View.OnClickListener buttonTextSizeChoiceListener = new View.OnClickListener(){
            public void onClick(View V){
                buttonSmallTextSize.setVisibility(View.VISIBLE);
                buttonMediumTextSize.setVisibility(View.VISIBLE);
                buttonLargeTextSize.setVisibility(View.VISIBLE);


            }
        };

        //change the text size in NoteBody to small
        buttonSmallTextSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor= MainActivity.sharedPreferences.edit();
                editor.putInt(MainActivity.textSizeKey,smallSize); // update the user text size choice
                editor.apply();//save changes
                NoteBody.textSize= MainActivity.sharedPreferences.getInt(MainActivity.textSizeKey,25);//load user choice
                setInvisibilityTextSizeButton();
            }
        });

        //change the text size in NoteBody to medium
        buttonMediumTextSize.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor= MainActivity.sharedPreferences.edit();
                editor.putInt(MainActivity.textSizeKey,mediumSize);
                editor.apply();
                NoteBody.textSize= MainActivity.sharedPreferences.getInt(MainActivity.textSizeKey,25);
                setInvisibilityTextSizeButton();
            }
        });

        //change the text size in NoteBody to large
        buttonLargeTextSize.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor= MainActivity.sharedPreferences.edit();
                editor.putInt(MainActivity.textSizeKey,largeSize);
                editor.apply();
                NoteBody.textSize= MainActivity.sharedPreferences.getInt(MainActivity.textSizeKey,25);
                setInvisibilityTextSizeButton();
        }

        });


        //create Checked Change listener for switchDarkMode
    CompoundButton.OnCheckedChangeListener switchDarkModeListener = new CompoundButton.OnCheckedChangeListener()
        {
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked){
        //implementation

            //Checked if Change switch
         if(isChecked){
              AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
         }
         else {
             AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
         }
    }
};

        //create click listener for switchEnterSave
        View.OnClickListener switchEnterSaveListener = new View.OnClickListener(){
            public void onClick(View V){
                //implementation
            }
        };

        //setting buttonBack to listen to "buttonBackListener".
        buttonBack.setOnClickListener(buttonBackListener);
        //setting buttonSortByChoice to listen to "buttonSortByChoiceListener".
        buttonSortByChoice.setOnClickListener(buttonSortByChoiceListener);
        //setting buttonTextSizeChoice to listen to "buttonTextSizeChoiceListener".
        buttonTextSizeChoice.setOnClickListener(buttonTextSizeChoiceListener);
        //setting switchDarkMode to listen to "switchDarkModeListener"
        switchDarkMode.setOnCheckedChangeListener(switchDarkModeListener);
        //setting switchEnterSave to listener to "switchEnterSaveListener"
        switchEnterSave.setOnClickListener(switchEnterSaveListener);







    }

    //set Invisibility of size button`s
    public void setInvisibilityTextSizeButton(){
        buttonSmallTextSize.setVisibility(View.INVISIBLE);
        buttonMediumTextSize.setVisibility(View.INVISIBLE);
        buttonLargeTextSize.setVisibility(View.INVISIBLE);
    }

}




