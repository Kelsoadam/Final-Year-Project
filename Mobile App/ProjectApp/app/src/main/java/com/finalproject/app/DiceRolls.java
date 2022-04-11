package com.finalproject.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class DiceRolls extends AppCompatActivity {

    public static int result;
    static Random roll = new Random();

    /*
    These methods generate a random int from 1 to x based on the dice,
    once the roll is completed it is converted to a string and used to
    change the .text value of the result label to the roll result value
    */

    static int D4() {
        return roll.nextInt(4) +1;
    }
    static int D6() {
        return roll.nextInt(6) +1;
    }
    static int D8() {
        return roll.nextInt(8) +1;
    }
    static int D10() {
        return roll.nextInt(10) +1;
    }
    static int D12() {
        return roll.nextInt(12) +1;
    }
    static int D20() {
        return roll.nextInt(20) +1;
    }
    static int D100() {
        return roll.nextInt(100) +1;
    }

}