package com.finalproject.app;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
    int fails = 0;
    int successes = 0;

    // Declaring buttons for skill checks
    Button acrobaticsCheck;
    Button animal_handlingCheck;
    Button arcanaCheck;
    Button athleticsCheck;
    Button deceptionCheck;
    Button historyCheck;
    Button insightCheck;
    Button intimidationCheck;
    Button investigationCheck;
    Button medicineCheck;
    Button natureCheck;
    Button perceptionCheck;
    Button performanceCheck;
    Button persuasionCheck;
    Button religionCheck;
    Button sleight_of_handCheck;
    Button stealthCheck;
    Button survivalCheck;

    // Declaring buttons for stat checks
    Button strCheck;
    Button dexCheck;
    Button conCheck;
    Button intCheck;
    Button wisCheck;
    Button charCheck;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView diceRoll, mod;
    String rollName;
    Button close;

    int diceResult;

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

        dsSuccesses = findViewById(R.id.Successes);
        dsFails = findViewById(R.id.fails);

        acrobaticsCheck = findViewById(R.id.btnAcrobatics);
        animal_handlingCheck = findViewById(R.id.btnAnimal);
        arcanaCheck = findViewById(R.id.btnArcana);
        athleticsCheck = findViewById(R.id.btnAthletics);
        deceptionCheck = findViewById(R.id.btnDeception);
        historyCheck = findViewById(R.id.btnHistory);
        insightCheck = findViewById(R.id.btnInsight);
        intimidationCheck = findViewById(R.id.btnIntimidation);
        investigationCheck = findViewById(R.id.btnInvestigation);
        medicineCheck = findViewById(R.id.btnMedicine);
        natureCheck = findViewById(R.id.btnNature);
        perceptionCheck = findViewById(R.id.btnPerception);
        performanceCheck = findViewById(R.id.btnPerformance);
        persuasionCheck = findViewById(R.id.btnPersuasion);
        religionCheck = findViewById(R.id.btnReligion);
        sleight_of_handCheck = findViewById(R.id.btnSoH);
        stealthCheck = findViewById(R.id.btnStealth);
        survivalCheck = findViewById(R.id.btnSurvival);


        content();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStrSave:
                rollName = "Strength Save:";
                createRollDialog(rollName, strMod);
                break;
            case R.id.btnStrength:
                rollName = "Strength Check:";
                createRollDialog(rollName, strMod);
                break;
            case R.id.btnAthletics:
                rollName = "Athletics Check:";
                createRollDialog(rollName, strMod);
                break;

            case R.id.btnInit:
                rollName = "Initiative:";
                createRollDialog(rollName, dexMod);
                break;
            case R.id.btnDexSave:
                rollName = "Dexterity Save:";
                createRollDialog(rollName, dexMod);
                break;
            case R.id.btnDexterity:
                rollName = "Dexterity:";
                createRollDialog(rollName, dexMod);
                break;
            case R.id.btnAcrobatics:
                rollName = "Acrobatics Check:";
                createRollDialog(rollName, dexMod);
                break;
            case R.id.btnSoH:
                rollName = "Sleight of Hand Check:";
                createRollDialog(rollName, dexMod);
                break;
            case R.id.btnStealth:
                rollName = "Stealth Check:";
                createRollDialog(rollName, dexMod);
                break;

            case R.id.btnConSave:
                rollName = "Constitution Save:";
                createRollDialog(rollName, conMod);
                break;
            case R.id.btnConstitution:
                rollName = "Constitution Check:";
                createRollDialog(rollName, conMod);
                break;

            case R.id.btnIntSave:
                rollName = "Intelligence Save:";
                createRollDialog(rollName, intMod);
                break;
            case R.id.btnIntelligence:
                rollName = "Intelligence Check:";
                createRollDialog(rollName, intMod);
                break;
            case R.id.btnArcana:
                rollName = "Arcana Check:";
                createRollDialog(rollName, intMod);
                break;
            case R.id.btnHistory:
                rollName = "History Check:";
                createRollDialog(rollName, intMod);
                break;
            case R.id.btnInvestigation:
                rollName = "Investigation Check:";
                createRollDialog(rollName, intMod);
                break;
            case R.id.btnNature:
                rollName = "Nature Check:";
                createRollDialog(rollName, intMod);
                break;
            case R.id.btnReligion:
                rollName = "Religion Check:";
                createRollDialog(rollName, intMod);
                break;

            case R.id.btnWisSave:
                rollName = "Wisdom Save:";
                createRollDialog(rollName, wisMod);
                break;
            case R.id.btnWisdom:
                rollName = "Wisdom Check:";
                createRollDialog(rollName, wisMod);
                break;
            case R.id.btnAnimal:
                rollName = "Animal Handling Check:";
                createRollDialog(rollName, wisMod);
                break;
            case R.id.btnInsight:
                rollName = "Insight Check:";
                createRollDialog(rollName, wisMod);
                break;
            case R.id.btnMedicine:
                rollName = "Medicine Check:";
                createRollDialog(rollName, wisMod);
                break;
            case R.id.btnPerception:
                rollName = "Perception Check:";
                createRollDialog(rollName, wisMod);
                break;
            case R.id.btnSurvival:
                rollName = "Survival Check:";
                createRollDialog(rollName, wisMod);
                break;

            case R.id.btnCharSave:
                rollName = "Charisma Save:";
                createRollDialog(rollName, charMod);
                break;
            case R.id.btnCharisma:
                rollName = "Charisma Check:";
                createRollDialog(rollName, charMod);
                break;
            case R.id.btnDeception:
                rollName = "Deception Check:";
                createRollDialog(rollName, charMod);
                break;
            case R.id.btnIntimidation:
                rollName = "Intimidation Check:";
                createRollDialog(rollName, charMod);
                break;
            case R.id.btnPerformance:
                rollName = "Performance Check:";
                createRollDialog(rollName, charMod);
                break;
            case R.id.btnPersuasion:
                rollName = "Persuasion Check:";
                createRollDialog(rollName, charMod);
                break;

            case R.id.btnDeathSave:
                deathSave();
                if(successes >= 3){
                    dsSuccesses.setText("You get back up!");
                    dsFails.setText("");
                    fails = 0; successes = 0;
                }
                else if(fails >= 3){
                    dsFails.setText("You are dead...");
                    dsSuccesses.setText("");
                    fails = 0; successes = 0;
                }
                break;
            case R.id.btnDeathReset:
                fails = 0;
                successes = 0;
                dsSuccesses.setText("");
                dsFails.setText("");
                break;
        }
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

            resetMods(); // Calls method that updates modifiers
            setSpeed(); // Calls method that sets speed based on chosen race
            setButtons(); // Calls method that sets the .text value of buttons to corresponding mod


        }
        refresh(2000);
        // Calls the refresh method to refresh our front end modifiers after x milliseconds
    }

    @SuppressLint("SetTextI18n")
    public void resetMods() {
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

    public void setSpeed() {
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
    public void setButtons() {

        rollInitiative.setText(Integer.toString(dexMod)); // Sets Initiative button to equal our mod

        // -------------------------------------------
        // Stat Check and Saving Throw Buttons
        // -------------------------------------------
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
        // ---------------------------------------------------
        // Skill Check Buttons
        // ---------------------------------------------------
        // Strength
        athleticsCheck.setText(Integer.toString(strMod));

        // Dexterity
        acrobaticsCheck.setText(Integer.toString(dexMod));
        sleight_of_handCheck.setText(Integer.toString(dexMod));
        stealthCheck.setText(Integer.toString(dexMod));

        // Intelligence
        arcanaCheck.setText(Integer.toString(intMod));
        historyCheck.setText(Integer.toString(intMod));
        investigationCheck.setText(Integer.toString(intMod));
        natureCheck.setText(Integer.toString(intMod));
        religionCheck.setText(Integer.toString(intMod));

        // Wisdom
        animal_handlingCheck.setText(Integer.toString(wisMod));
        insightCheck.setText(Integer.toString(wisMod));
        medicineCheck.setText(Integer.toString(wisMod));
        perceptionCheck.setText(Integer.toString(wisMod));
        survivalCheck.setText(Integer.toString(wisMod));

        // Charisma
        deceptionCheck.setText(Integer.toString(charMod));
        intimidationCheck.setText(Integer.toString(charMod));
        performanceCheck.setText(Integer.toString(charMod));
        persuasionCheck.setText(Integer.toString(charMod));

        // ------------------------------------------

    }

    public void deathSave() {
        diceResult = DiceRolls.D20();
        rollName = "Death Save:";
        createRollDialog(rollName, diceResult);
    }

    @SuppressLint("SetTextI18n")
    public void createRollDialog(String rollName, int mod) {
        dialogBuilder = new AlertDialog.Builder(this);
        final View diceRollPopup = getLayoutInflater().inflate(R.layout.popup, null);

        diceRoll = (TextView) diceRollPopup.findViewById(R.id.rollResult);
        close = (Button) diceRollPopup.findViewById(R.id.closeButton);

        if (rollName.equals("Death Save:")) {
            if (diceResult == 20) {
                diceRoll.setText(rollName + "\n(" + Integer.toString(diceResult) + ")" + "\nCritical Success");
                successes += 2;
                dsSuccesses.setText(Integer.toString(successes));
            } else if (diceResult >= 10) {
                diceRoll.setText(rollName + "\n(" + Integer.toString(diceResult) + ")" + "\nSuccess");
                successes++;
                dsSuccesses.setText(Integer.toString(successes));
            } else if (diceResult == 1) {
                diceRoll.setText(rollName + "\n(" + Integer.toString(diceResult) + ")" + "\nCritical Fail");
                fails += 2;
                dsFails.setText(Integer.toString(fails));
            } else {
                diceRoll.setText(rollName + "\n(" + Integer.toString(diceResult) + ")" + "\nFail");
                fails++;
                dsFails.setText(Integer.toString(fails));
            }
        } else {
            diceResult = DiceRolls.D20();
            diceRoll.setText(rollName + "\n(" + Integer.toString(diceResult) + ") + " +
                    Integer.toString(mod) + " = " + (diceResult + mod));
        }


        dialogBuilder.setView(diceRollPopup);
        dialog = dialogBuilder.create();
        dialog.show();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Closes dialog window
                dialog.dismiss();
            }
        });
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