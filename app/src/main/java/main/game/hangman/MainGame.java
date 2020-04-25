package main.game.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Random;

public class MainGame extends AppCompatActivity {

    private String finalWord = "";
    private int amountOfLetters = 0;
    private char guess = 'h';
    private static ArrayList<String> test = new ArrayList<>();
    private boolean winOrLose;

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

        winOrLose = userGuessesCharInWord(finalWord, guess);


        launchPlayerWinOrLoseActivity(winOrLose);


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

    private static void findCharInWord(String word, char guess){
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

    private static boolean userGuessesCharInWord(String finalWord, char guess){
        int amountOfGuesses = 0;
        boolean chancesLeft = true;
        game:   //makes it possible to break out from while loop from a nested loop
        while (chancesLeft) {

            //user input

            if(test.contains(guess)) {
                findCharInWord(finalWord, guess);
            }
            else{
                System.out.println("wrong");
                amountOfGuesses = amountOfGuesses + 1;
            }
            if(test.contains(" _ ") == false){
                break game;
            }
            if (amountOfGuesses == 7){
                chancesLeft = false;
                break game;
            }
        }

        return chancesLeft;
    }

    private void launchPlayerWinOrLoseActivity(boolean winOrLose){
        Intent intent = new Intent(this, MainGame.class);
        if (winOrLose == true){
         //   intent = new Intent(this, )
        }
        else{
         //   intent = new Intent(this, )
        }
        startActivity(intent);
    }

}