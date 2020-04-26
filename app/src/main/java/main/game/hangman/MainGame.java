package main.game.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainGame extends AppCompatActivity {

    private String finalWord = "";
    private int amountOfLetters = 0;
    private EditText charInput;
    private TextView displayWordForUser;
    private static ArrayList<String> containerForWordDisplay = new ArrayList<>();
    private boolean winOrLose;

    private static MainModel importantData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_main);

        importantData = new MainModel();
        charInput = (EditText) findViewById(R.id.charGuess);
        displayWordForUser = (TextView)findViewById(R.id.finalWordDisplay);

        randomizeFinalWord();

        finalWord = importantData.get_finalWord();

        amountOfLetters = findCharAmountOfWord(finalWord);

        lengthOfWordDisplay(amountOfLetters, displayWordForUser, containerForWordDisplay);

        winOrLose = userGuessesCharInWord(finalWord, charInput, displayWordForUser);

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

    private static void findCharInWord(String word, char guess, TextView displayWordForUser){
        for (int i = 0; i < word.toCharArray().length; i++){
            if(word.toCharArray()[i] == guess){
                containerForWordDisplay.set(i, String.valueOf(guess));
                displayWordForUser.append(containerForWordDisplay.get(i));
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

    private static void lengthOfWordDisplay(int amountOfLetters, TextView displayWordForUser, ArrayList<String> containerForWordDisplay){
        for (int i = 0; i < amountOfLetters; i++){
            containerForWordDisplay.add(" _ ");
            displayWordForUser.append(containerForWordDisplay.get(i));
        }
    }

    private static boolean userGuessesCharInWord(String finalWord, EditText charInput, TextView displayWordForUser){
        int amountOfGuesses = 0;
        boolean chancesLeft = true;
        char guess;
        game:   //makes it possible to break out from while loop from a nested loop
        while (chancesLeft) {
        guess = charInput.getText().toString().toCharArray()[0];

            if(containerForWordDisplay.contains(guess)) {
                findCharInWord(finalWord, guess, displayWordForUser);
            }
            else{
                System.out.println("wrong");
                amountOfGuesses = amountOfGuesses + 1;
            }
            if(containerForWordDisplay.contains(" _ ") == false){
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
            intent = new Intent(this, MainWinPopUp.class);
        }
        else{
            intent = new Intent(this, MainLosePopUp.class);
        }
        startActivity(intent);
    }

}