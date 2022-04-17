package com.finalproject.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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

    // Declaring EditText components for User stats input
    EditText strInput;
    EditText dexInput;
    EditText conInput;
    EditText intInput;
    EditText wisInput;
    EditText charInput;

    // Declaring some default stats to be read-in by the mod calculator
    String getSTR = "10";
    String getDEX = "10";
    String getCON = "10";
    String getINT = "10";
    String getWIS = "10";
    String getCHAR = "10";

    // Variables needed for the character speed logic
    Spinner raceSpinner;
    String selectedRace = "";
    TextView speed;

    // Declaring buttons for misc functions; Initiative, Resting, and Leveling up
    Button rollInitiative;
    Button rest;
    Button lvlup;

    // Declaring buttons for saving throws
    Button savingStr;
    Button savingDex;
    Button savingCon;
    Button savingInt;
    Button savingWis;
    Button savingChar;

    // Declaring buttons and TextViews for death saves;
    Button rollDeathSave;
    TextView dsSuccesses;
    TextView dsFails;

    // Declaring buttons for skill checks

    // Declaring buttons for stat checks
    Button strCheck;
    Button dexCheck;
    Button conCheck;
    Button intCheck;
    Button wisCheck;
    Button charCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        allocateActivityTitle("Character Sheet");

        /*
        The code from this point of in this method works to set the backend variables
        to be linked with the components on the front end through the use of IDs
        by using findViewById(R.id.COMPONENT_ID)
        */

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

        rollInitiative = findViewById(R.id.btnInit);
        rest = findViewById(R.id.btnRest);
        lvlup = findViewById(R.id.btnLvlUp);

        strCheck = findViewById(R.id.btnStrength);
        dexCheck = findViewById(R.id.btnDexterity);
        conCheck = findViewById(R.id.btnConstitution);
        intCheck = findViewById(R.id.btnIntelligence);
        wisCheck = findViewById(R.id.btnWisdom);
        charCheck = findViewById(R.id.btnCharisma);

        savingStr = findViewById(R.id.btnStrSave);
        savingDex = findViewById(R.id.btnDexSave);
        savingCon = findViewById(R.id.btnConSave);
        savingInt = findViewById(R.id.btnIntSave);
        savingWis = findViewById(R.id.btnWisSave);
        savingChar = findViewById(R.id.btnCharSave);

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

            resetMods();
            setSpeed();
            setButtons();

        }
        refresh(2000);
        // Calls the refresh method to refresh our front end modifiers after x milliseconds
    }

    public void resetMods(){
        // This method sets stat modifiers to equal the inputted stat value (-10), halved then rounded down
        // i.e. Strength = 16, so 16 - 10 = 6, / 2 = 3; which is the correct modifier

        getSTR = strInput.getText().toString();
        modCalc = (Double.parseDouble(getSTR) - 10) / 2;
        strMod = (int) Math.floor(modCalc);

        getDEX = dexInput.getText().toString();
        modCalc = (Double.parseDouble(getDEX) - 10) / 2;
        dexMod = (int) Math.floor(modCalc);

        TextView initiative = (TextView) findViewById(R.id.textInit_score);
        initiative.setText(Integer.toString(dexMod));
        // Sets our TextView for showing our initiative modifier

        getCON = conInput.getText().toString();
        modCalc = (Double.parseDouble(getCON) - 10) / 2;
        conMod = (int) Math.floor(modCalc);


        getINT = intInput.getText().toString();
        modCalc = (Double.parseDouble(getINT) - 10) / 2;
        intMod = (int) Math.floor(modCalc);


        getWIS = wisInput.getText().toString();
        modCalc = (Double.parseDouble(getWIS) - 10) / 2;
        wisMod = (int) Math.floor(modCalc);


        getCHAR = charInput.getText().toString();
        modCalc = (Double.parseDouble(getCHAR) - 10) / 2;
        charMod = (int) Math.floor(modCalc);
    }

    public void setSpeed(){
        // Sets the speed value on the sheet to be equal to
        // the speed associated with the selected race

        selectedRace = String.valueOf(raceSpinner.getSelectedItem().toString());
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
            case "Elf":
            case "Firbolg":
            case "Genasi":
            case "Gith":
            case "Goblin":
            case "Goliath":
            case "Half-Elf":
            case "Half-Orc":
            case "Hobgoblin":
            case "Human":
            case "Kalashtar":
            case "Kenku":
            case "Kobold":
            case "Loxodon":
            case "Minotaur":
            case "Orc":
            case "Shifter":
            case "Simic Hybrid":
            case "Tabaxi":
            case "Tiefling":
            case "Tortle":
            case "Vedalken":
            case "Warforged":
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

    @SuppressLint("SetTextI18n")
    public void setButtons(){

        rollInitiative.setText(Integer.toString(dexMod)); // Sets Initiative button to equal our mod

        strCheck.setText(Integer.toString(strMod));
        savingStr.setText(Integer.toString(strMod));

        dexCheck.setText(Integer.toString(dexMod));
        savingDex.setText(Integer.toString(dexMod));

        conCheck.setText(Integer.toString(conMod));
        savingCon.setText(Integer.toString(conMod));

        intCheck.setText(Integer.toString(intMod));
        savingInt.setText(Integer.toString(intMod));

        wisCheck.setText(Integer.toString(wisMod));
        savingWis.setText(Integer.toString(wisMod));

        charCheck.setText(Integer.toString(charMod));
        savingChar.setText(Integer.toString(charMod));

    }

    private void refresh(int milliseconds) {
        // This method refreshes our front end to show the new modifiers of our sheet

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