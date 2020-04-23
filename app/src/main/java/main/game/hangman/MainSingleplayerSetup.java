package main.game.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
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

        buttonEasy = findViewById(R.id.easyButton);
        buttonEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonEasy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean active) {
                        if(active) {
                            importantData.set_difficulty(1);
                        }
                        else { }
                    }
                });
            }
        });

        buttonMedium = findViewById(R.id.mediumButton);
        buttonMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonMedium.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean active) {
                        if(active) {
                            importantData.set_difficulty(2);
                        }
                        else { }
                    }
                });
            }
        });

        buttonHard = findViewById(R.id.hardButton);
        buttonHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonHard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean active) {
                        if(active) {
                            importantData.set_difficulty(3);
                            saker();
                        }
                        else { }
                    }
                });
            }
        });

    }

    private void saker (){

        if(buttonEasy.isChecked()){
            buttonMedium.setChecked(false);
            buttonHard.setChecked(false);
        }
        else if(buttonMedium.isChecked()){
            buttonEasy.setChecked(false);
            buttonHard.setChecked(false);
        }
        else if(buttonHard.isChecked()){
            buttonEasy.setChecked(false);
            buttonMedium.setChecked(false);
        }
    }



}

