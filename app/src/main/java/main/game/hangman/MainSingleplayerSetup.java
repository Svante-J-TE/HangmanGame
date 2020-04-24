package main.game.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.ToggleButton;

public class MainSingleplayerSetup extends AppCompatActivity {

    private MainModel importantData;
    private ToggleButton buttonEasy;
    private ToggleButton buttonMedium;
    private ToggleButton buttonHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleplayersetup_main);

        importantData = new MainModel();

        final RadioButton buttonEasy = findViewById(R.id.easyButton);
        buttonEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                importantData.set_difficulty(1);
            }
        });

        final RadioButton buttonMedium = findViewById(R.id.mediumButton);
        buttonMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                importantData.set_difficulty(2);
            }
        });

        final RadioButton buttonHard = findViewById(R.id.hardButton);
        buttonHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                importantData.set_difficulty(3);
            }
        });

        final Button buttonReturn = findViewById(R.id.returnButton);
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final Button buttonConfirm = findViewById(R.id.confirmButton);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(MainSingleplayerSetup.this, MainGame.class));
            }
        });

    }

}