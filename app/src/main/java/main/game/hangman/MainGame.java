package main.game.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MainGame extends AppCompatActivity {

    private String finalWord = "";
    private int amountOfLetters = 0;
    private char guess = 'h';
    private static ArrayList<String> test = new ArrayList<>();

    private static MainModel importantData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_main);

        importantData = new MainModel();


        randomizeFinalWord();

        finalWord = importantData.get_finalWord();

        amountOfLetters = findCharAmountOfWord(finalWord);

        lenghtOfWordDisplay(amountOfLetters);

            //findCharInWord(finalWord, guess, amountOfLetters);


            findCharInWord2(finalWord, guess);


                System.out.println(test);







    }

    private static void randomizeFinalWord(){
        if (importantData.get_difficulty() == 1){
            Random random = new Random();
            int index = random.nextInt(importantData.get_wordList().size());
            // importantData.set_finalWord(importantData.get_wordList().get(index));
            importantData.set_finalWord("hej"); //ta bort detta
            System.out.println(importantData.get_finalWord());
        }

    }

    private static void findCharInWord(String word, char guess, int amountOfLetters){
        for (int i = 0; i < word.toCharArray().length; i++){
            if(word.toCharArray()[i] == guess){
                for (int i2 = 0; i < amountOfLetters; i2++){
                    if(word.toCharArray()[i2] == guess){
                        System.out.print(i2);
                    }
                    else {
                        System.out.print("_ ");
                    }
                }
            }
        }
    }

    private static void findCharInWord2(String word, char guess){
        for (int i = 0; i < word.toCharArray().length; i++){
            if(word.toCharArray()[i] == guess){
                test.set(i, String.valueOf(guess));

            }
        }
    }

    private static int findCharAmountOfWord(String word){
        int amountOfLetters = 0;
        for (int i = 0; i < word.toCharArray().length; i++){
            amountOfLetters = amountOfLetters + 1;
        }
        return amountOfLetters;
    }

    private static void lenghtOfWordDisplay(int amountOfLetters){
        for (int i = 0; i < amountOfLetters; i++){
            test.add(" _ ");
        }
    }

}