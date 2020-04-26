package main.game.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainGame extends AppCompatActivity {

    private String finalWord = "";
    private int amountOfLetters = 0;
    private int amountOfGuesses = 0;
    private EditText charInput;
    private TextView displayWordForUser;
    private static ArrayList<String> containerForWordDisplay = new ArrayList<>();
    private static ArrayList<String > alreadyGuessedCharacters = new ArrayList<>();
    private boolean chancesLeft = true;
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

        final Button buttonSubmit = findViewById(R.id.submitButton);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amountOfGuesses = userGuessesCharInWord(finalWord, charInput, displayWordForUser, amountOfLetters, amountOfGuesses, alreadyGuessedCharacters);

                if(containerForWordDisplay.contains(" _ ") == false) {
                    launchPlayerWinOrLoseActivity(chancesLeft);
                }
                if (amountOfGuesses == 7){
                    chancesLeft = false;
                    launchPlayerWinOrLoseActivity(chancesLeft);
                }
            }
        });

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

    private static void placeCharInWord(String word, char guess, TextView displayWordForUser, int amountOfLetters){
        for (int i = 0; i < word.toCharArray().length; i++){
            if(word.toCharArray()[i] == guess){

                containerForWordDisplay.set(i, Character.toString(guess));
                displayWordForUser.setText("");
                for(int j = 0; j < amountOfLetters; j++) {
                    displayWordForUser.append(containerForWordDisplay.get(j));
                }

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

    private static int userGuessesCharInWord(String finalWord, EditText charInput, TextView displayWordForUser, int amountOfLetters, int amountOfGuesses, ArrayList alreadyGuessedCharacters){
        char guess;

            guess = charInput.getText().toString().toCharArray()[0];

            if(finalWord.indexOf(guess) != -1) {
                if (alreadyGuessedCharacters.contains(guess)){
                    //add meseage about about already guessing that char
                }
                else{
                    placeCharInWord(finalWord, guess, displayWordForUser, amountOfLetters);
                }
            }
            else{
                if (alreadyGuessedCharacters.contains(guess)){
                    //add meseage about about already guessing that char
                }
                else {
                    //message about wrong char + add to array of wrong chars
                    amountOfGuesses = amountOfGuesses + 1;
                }
            }
        alreadyGuessedCharacters.add(guess);
        return amountOfGuesses;
    }

    private void launchPlayerWinOrLoseActivity(boolean winOrLose){
        Intent intent;
        if (winOrLose == true){
            intent = new Intent(this, MainWinPopUp.class);
        }
        else{
            intent = new Intent(this, MainLosePopUp.class);
        }
        startActivity(intent);
    }

}