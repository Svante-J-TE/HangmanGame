package main.game.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainGame extends AppCompatActivity {

    private String finalWord = "";
    private int amountOfLetters = 0;
    private int amountOfGuesses = 0;
    private static ImageView hangmanDisplay;
    private static EditText charInput;
    private static TextView displayWordForUser;
    private static TextView displayWrongAnswers;
    private static ArrayList<String> containerForWordDisplay = new ArrayList<>();
    private static ArrayList<String> alreadyGuessedCharacters = new ArrayList<>();
    private static ArrayList<String> wrongGuesses = new ArrayList<>();
    private boolean chancesLeft;

    private static MainModel importantData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_main);

        importantData = new MainModel();
        hangmanDisplay = (ImageView) findViewById(R.id.imageView);
        charInput = (EditText) findViewById(R.id.charGuess);
        displayWordForUser = (TextView)findViewById(R.id.finalWordDisplay);
        displayWrongAnswers = (TextView) findViewById(R.id.wrongAnswerDisplay);

        hangmanDisplay.setImageResource(R.drawable.hangman_default);

        // importantData.wordListCreator(); //do something with difficulty in this method

        randomizeFinalWord();

        finalWord = importantData.get_finalWord();

        amountOfLetters = findCharAmountOfWord(finalWord);

        lengthOfWordDisplay(amountOfLetters, displayWordForUser, containerForWordDisplay);

        final Button buttonSubmit = findViewById(R.id.submitButton);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amountOfGuesses = userGuessesCharInWord(finalWord, amountOfLetters, amountOfGuesses, alreadyGuessedCharacters, wrongGuesses);

                if(containerForWordDisplay.contains(" _ ") == false) {
                    chancesLeft = true;
                    launchPlayerWinOrLoseActivity(chancesLeft);
                }
                if (amountOfGuesses == 11){
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
        }

    }

    private static void placeCharInWord(String word, char guess, int amountOfLetters){
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

    private static int userGuessesCharInWord(String finalWord, int amountOfLetters, int amountOfWrongGuesses, ArrayList<String> alreadyGuessedCharacters, ArrayList<String> wrongGuesses){
        //TODO, change to string
        char guess;

            guess = charInput.getText().toString().toCharArray()[0];
            guess = Character.toUpperCase(guess);
            finalWord = finalWord.toUpperCase();

            if(!Character.toString(guess).matches("[a-zA-Z]+")){
                //add message about you have to guess a letter and not numbers and what not
                return amountOfWrongGuesses;
            }

            if(finalWord.indexOf(guess) != -1) {
                if (alreadyGuessedCharacters.contains(guess)){
                    //add meseage about about already guessing that char
                }
                else{
                    placeCharInWord(finalWord, guess, amountOfLetters);
                }
            }
            else{
                if (alreadyGuessedCharacters.contains(Character.toString(guess))){
                    //add meseage about about already guessing that char
                    return amountOfWrongGuesses;
                }
                else {
                    //message about wrong char + add to array of wrong chars
                        placeInWrongDisplay(amountOfWrongGuesses, wrongGuesses, guess);
                        alreadyGuessedCharacters.add(Character.toString(guess));
                        amountOfWrongGuesses = amountOfWrongGuesses + 1;
                        displayHangman(amountOfWrongGuesses);
                }
            }
        return amountOfWrongGuesses;
    }

    private static int placeInWrongDisplay(int amountOfWrongGuesses, ArrayList<String> wrongGuesses, char guess){
        wrongGuesses.add(Character.toString(guess));

        displayWrongAnswers.setText(displayWrongAnswers.getText()+(" "+guess));

        amountOfWrongGuesses = amountOfWrongGuesses + 1;
        return amountOfWrongGuesses;
    }

    private static void displayHangman(int amountOfWrongGuesses){
        if (amountOfWrongGuesses == 1){
            hangmanDisplay.setImageResource(R.drawable.hangman_image1);
        }
        if (amountOfWrongGuesses == 2){
            hangmanDisplay.setImageResource(R.drawable.hangman_image2);
        }
        if (amountOfWrongGuesses == 3){
            hangmanDisplay.setImageResource(R.drawable.hangman_image3);
        }
        if (amountOfWrongGuesses == 4){
            hangmanDisplay.setImageResource(R.drawable.hangman_image4);
        }
        if (amountOfWrongGuesses == 5){
            hangmanDisplay.setImageResource(R.drawable.hangman_image5);
        }
        if (amountOfWrongGuesses == 6){
            hangmanDisplay.setImageResource(R.drawable.hangman_image6);
        }
        if (amountOfWrongGuesses == 7){
            hangmanDisplay.setImageResource(R.drawable.hangman_image7);
        }
        if (amountOfWrongGuesses == 8){
            hangmanDisplay.setImageResource(R.drawable.hangman_image8);
        }
        if (amountOfWrongGuesses == 9){
            hangmanDisplay.setImageResource(R.drawable.hangman_image9);
        }
        if (amountOfWrongGuesses == 10){
            hangmanDisplay.setImageResource(R.drawable.hangman_image10);
        }
        if (amountOfWrongGuesses == 11){
            hangmanDisplay.setImageResource(R.drawable.hangman_image11);
        }

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