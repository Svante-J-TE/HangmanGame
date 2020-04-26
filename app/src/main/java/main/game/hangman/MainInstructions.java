package main.game.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainInstructions extends AppCompatActivity {

    private int buttonPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions_main);

        final Button buttonSingle = findViewById(R.id.singleInstrucButton);
        buttonSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonPressed = 1;
                launchActivity(buttonPressed);
            }
        });

        final Button buttonMulti = findViewById(R.id.multiInstrucButton);
        buttonMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonPressed = 2;
                launchActivity(buttonPressed);
            }
        });
    }

    private void launchActivity(int buttonPressed){
        Intent intent = new Intent(MainInstructions.this, MainInstructions.class);
        switch (buttonPressed){
            case 1:
                intent = new Intent(MainInstructions.this, PopUpInstructionsSingle.class);
                break;

            case 2:
                intent = new Intent(MainInstructions.this, PopUpInstructionsMulti.class);
                break;
        }
        startActivity(intent);
    }
}
