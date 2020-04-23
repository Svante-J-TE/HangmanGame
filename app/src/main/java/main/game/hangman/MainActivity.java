package main.game.hangman;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private MainModel importantData;
    private int buttonPressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        importantData = new MainModel();
        importantData.set_answer("hej");    //Gör så här för att ge värden och för att ta värden från den andra klassen

        final Button buttonSingleplayer = findViewById(R.id.singlePlayerButton);
        buttonSingleplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonPressed = 1;
                launchActivity();
            }
        });

        final Button buttonMultiplayer = findViewById(R.id.multiPlayerButton);
        buttonMultiplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonPressed = 2;
                launchActivity();
            }
        });
        
        final Button buttonInstructions = findViewById(R.id.instructionsButton);
        buttonInstructions.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                buttonPressed = 3;
                launchActivity();
            }
        });

        final Button buttonWordlist = findViewById(R.id.wordlistButton);
        buttonWordlist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                buttonPressed = 4;
                launchActivity();
            }
        });

        final Button buttonOptions = findViewById(R.id.optionsButton);
        buttonOptions.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                buttonPressed = 5;
                launchActivity();
            }
        });

        final Button buttonExit = findViewById(R.id.shutdownButton);
        buttonExit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }

    private void launchActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        switch(buttonPressed){
            case 1:
                intent = new Intent(this, MainSingleplayerSetup.class);
                break;

            case 2:
             //   intent = new Intent(this, .class);
                break;

            case 3:
             //   intent = new Intent(this, .class);
                break;

            case 4:
             //  intent = new Intent(this, .class);
                break;

            case 5:
                intent = new Intent(this, MainOptions.class);
                break;
        }
        startActivity(intent);
    }

}