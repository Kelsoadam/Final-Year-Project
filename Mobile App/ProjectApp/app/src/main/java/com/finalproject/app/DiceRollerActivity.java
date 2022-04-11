package com.finalproject.app;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.finalproject.app.databinding.ActivityDiceRollerBinding;

public class DiceRollerActivity extends DrawerBaseActivity {

    ActivityDiceRollerBinding activityDiceRollerBinding;

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDiceRollerBinding = ActivityDiceRollerBinding.inflate(getLayoutInflater());
        setContentView(activityDiceRollerBinding.getRoot());
        allocateActivityTitle("Dice Roller");

        txt = findViewById(R.id.textRollResult); // Sets txt to be the id of our result label
    }

    /*
    This switch is called on clicking the dice buttons,
    when the onClick is called the switch will check to
    see the id of the button that called it and call
    the method for the correlating dice roll; once the
    method has been called, the result will be used to set
    the text value of our TextView object to the rolled result
    */
    public void onClick(View v){
        switch(v.getId()){
            case R.id.d4button:
                txt.setText(Integer.toString(DiceRolls.D4()));
                break;
            case R.id.d6button:
                txt.setText(Integer.toString(DiceRolls.D6()));
                break;
            case R.id.d8button:
                txt.setText(Integer.toString(DiceRolls.D8()));
                break;
            case R.id.d10button:
                txt.setText(Integer.toString(DiceRolls.D10()));
                break;
            case R.id.d12button:
                txt.setText(Integer.toString(DiceRolls.D12()));
                break;
            case R.id.d20button:
                txt.setText(Integer.toString(DiceRolls.D20()));
                break;
            case R.id.d100button:
                txt.setText(Integer.toString(DiceRolls.D100()));
                break;
        }
    }
}