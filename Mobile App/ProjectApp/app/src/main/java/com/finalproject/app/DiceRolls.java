package com.finalproject.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class DiceRolls extends AppCompatActivity {

    TextView txt;

    Random roll = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_rolls);

        txt = findViewById(R.id.textRollResult); // Sets txt to be the id of our result label
    }

    /*
    This switch is called on clicking the dice buttons,
    when the onClick is called the switch will check to
    see the id of the button that called it and call
    the method for the correlating dice roll
    */
    public void onClick(View v){
        switch(v.getId()){
            case R.id.d4button:
                D4();
                break;
            case R.id.d6button:
                D6();
                break;
            case R.id.d8button:
                D8();
                break;
            case R.id.d10button:
                D10();
                break;
            case R.id.d12button:
                D12();
                break;
            case R.id.d20button:
                D20();
                break;
            case R.id.d100button:
                D100();
                break;
        }
    }

    /*
    These methods generate a random int from 1 to x based on the dice,
    once the roll is completed it is converted to a string and used to
    change the .text value of the result label to the roll result value
    */

    public void D4() {
        int val = roll.nextInt(4) +1;
        txt.setText(Integer.toString(val));
    }
    void D6() {
        int val = roll.nextInt(6) +1;
        txt.setText(Integer.toString(val));
    }
    void D8() {
        int val = roll.nextInt(8) +1;
        txt.setText(Integer.toString(val));
    }
    void D10() {
        int val = roll.nextInt(10) +1;
        txt.setText(Integer.toString(val));
    }
    void D12() {
        int val = roll.nextInt(12) +1;
        txt.setText(Integer.toString(val));
    }
    void D20() {
        int val = roll.nextInt(20) +1;
        txt.setText(Integer.toString(val));
    }
    void D100() {
        int val = roll.nextInt(100) +1;
        txt.setText(Integer.toString(val));
    }

}