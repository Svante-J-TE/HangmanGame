package main.game.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class MainGame extends AppCompatActivity {

    private static MainModel importantData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_main);

        importantData = new MainModel();

        final Button buttonHard = findViewById(R.id.button);
        buttonHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                importantData.wordListCreator();
                randomizeFinalWord();
            }
        });


    }

    private static void randomizeFinalWord(){
        if (importantData.get_difficulty() == 1){
            Random random = new Random();
            int index = random.nextInt(importantData.get_wordList().size());
            importantData.set_finalWord(importantData.get_wordList().get(index));
            System.out.println(importantData.get_finalWord());
        }

    }

}
