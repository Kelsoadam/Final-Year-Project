package com.finalproject.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This code sets up the button tat is used to navigate to the
        // dice roll activity
        button = (Button) findViewById(R.id.NavButtonDiceRolls);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiceRolls();
            }
        });
    }

    // method called when button to change activity is pressed to start the
    // dice roll activity
    public void openDiceRolls(){
        Intent intent = new Intent(this, DiceRolls.class);
        startActivity(intent);
    }
}