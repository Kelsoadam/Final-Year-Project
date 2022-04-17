package com.finalproject.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.finalproject.app.databinding.ActivityDiceRollerBinding;
import com.finalproject.app.databinding.ActivityMainBinding;

public class MainActivity extends DrawerBaseActivity {

    ActivityMainBinding activityMainBinding;

    // Declaring modifiers for stats which will be used for our button .text values
    // after being updated by our refresh() method
    int strMod = 0;
    int dexMod = 0;
    int conMod = 0;
    int intMod = 0;
    int wisMod = 0;
    int charMod = 0;

    double modCalc;

    EditText strInput;
    EditText dexInput;
    EditText conInput;
    EditText intInput;
    EditText wisInput;
    EditText charInput;

    String getSTR = "10";
    String getDEX = "10";
    String getCON = "10";
    String getINT = "10";
    String getWIS = "10";
    String getCHAR = "10";

    Spinner raceSpinner;
    String selectedRace = "";
    TextView speed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        allocateActivityTitle("Character Sheet");

        raceSpinner = findViewById(R.id.selectRace);
        ArrayAdapter<String> raceAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.races));
        raceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        raceSpinner.setAdapter(raceAdapter);

        Spinner classSpinner = (Spinner) findViewById(R.id.selectClass);
        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.classes));
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(classAdapter);

        Spinner alignSpinner = (Spinner) findViewById(R.id.selectAlignment);
        ArrayAdapter<String> alignAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.alignments));
        alignAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        alignSpinner.setAdapter(alignAdapter);

        strInput = findViewById(R.id.editTextStr);
        dexInput = findViewById(R.id.editTextDex);
        conInput = findViewById(R.id.editTextCon);
        intInput = findViewById(R.id.editTextInt);
        wisInput = findViewById(R.id.editTextWis);
        charInput = findViewById(R.id.editTextChar);

        speed = findViewById(R.id.textSpeed_score);


        content();
    }

    public void onClick(View v) {

    }

    public void content() {

        // Will check to see if all character stats have been assigned
        // and if not will not assign the stat modifiers
        if (!strInput.getText().toString().equals("") &&
                !dexInput.getText().toString().equals("") &&
                !conInput.getText().toString().equals("") &&
                !intInput.getText().toString().equals("") &&
                !conInput.getText().toString().equals("") &&
                !wisInput.getText().toString().equals("") &&
                !charInput.getText().toString().equals("")) {
            // Sets stat modifiers to equal the inputted stat value (-10), halved then rounded down
            // i.e. Strength = 16, so 16 - 10 = 6, / 2 = 3; which is the correct modifier
            getSTR = strInput.getText().toString();
            modCalc = (Double.parseDouble(getSTR) - 10) / 2;
            strMod = (int) Math.floor(modCalc);

            getDEX = dexInput.getText().toString();
            modCalc = (Double.parseDouble(getDEX) - 10) / 2;
            dexMod = (int) Math.floor(modCalc);

            TextView initiative = (TextView) findViewById(R.id.textInit_score);
            initiative.setText(Integer.toString(dexMod));


            String getCON = conInput.getText().toString();
            modCalc = (Double.parseDouble(getCON) - 10) / 2;
            conMod = (int) Math.floor(modCalc);


            String getINT = intInput.getText().toString();
            modCalc = (Double.parseDouble(getINT) - 10) / 2;
            intMod = (int) Math.floor(modCalc);


            String getWIS = wisInput.getText().toString();
            modCalc = (Double.parseDouble(getWIS) - 10) / 2;
            wisMod = (int) Math.floor(modCalc);


            String getCHAR = charInput.getText().toString();
            modCalc = (Double.parseDouble(getCHAR) - 10) / 2;
            charMod = (int) Math.floor(modCalc);

            selectedRace = String.valueOf(raceSpinner.getSelectedItem().toString());


//            if (selectedRace.equals("Orc")){
//                speed.setText(R.string.speed_Thirty);
//            }
//            else if(selectedRace.equals("")){
//                speed.setText(R.string.speed_switchTest);
//            }


            switch (selectedRace) {
                case "Dwarf":
                case "Gnome":
                case "Halfling":
                    speed.setText(R.string.speed_TwentyFive);
                    break;

                case "Aasimar":
                case "Bugbear":
                case "Changeling":
                case "Dragonborn":
                    case "Elf"






                    case "Yuan-ti Pureblood":
                    speed.setText(R.string.speed_Thirty);
                    break;
                case "Centaur":
                    speed.setText(R.string.speed_forty);
                    break;

                case "Aarakocra":
                    speed.setText(R.string.speed_Aarakocra);
                    break;

                case "Lizardfolk":
                case "Triton":
                    speed.setText(R.string.speed_LizardTriton);
                    break;

                case "Grung":
                    speed.setText(R.string.speed_Grung);
                    break;
            }
        }


        refresh(2000);
    }


    private void refresh(int milliseconds) {

        final Handler handler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                content();
            }
        };

        handler.postDelayed(runnable, milliseconds);
    }
}