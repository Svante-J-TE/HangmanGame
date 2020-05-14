package main.game.hangman;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    private MainModel importantData;
    private int buttonPressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        importantData = new MainModel();

        wordListCreator();

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

    /**
     * fills wordlist from a textfile
     */
    public void wordListCreator(){
        InputStream inputStream = getResources().openRawResource(R.raw.word_list_eng);
        Scanner reader = new Scanner(inputStream);
        while (reader.hasNext()){
            importantData.set_wordList(reader.nextLine().trim());
        }
    }

    /**
     * The method uses buttonPressed to determine which class is selected. Once the target class is determined
     * the method uses the method startActivity() to open the target class
     */
    private void launchActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        switch(buttonPressed){
            case 1:
                intent = new Intent(this, MainSingleplayerSetup.class);
                break;

            case 2:
                intent = new Intent(this, MainMultiplayerSetup.class);
                break;

            case 3:
                intent = new Intent(this, MainInstructions.class);
                break;

            case 4:
               intent = new Intent(this, MainWordList.class);
                break;

            case 5:
                intent = new Intent(this, MainOptions.class);
                break;
        }
        startActivity(intent);
    }

}