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

    /**
     * A method used to randomize a word from a wordList
     */
    private static void randomizeFinalWord(){
        if (importantData.get_difficulty() == 1){
            Random random = new Random();
            int index = random.nextInt(importantData.get_wordList().size());
            // importantData.set_finalWord(importantData.get_wordList().get(index));
            importantData.set_finalWord("hej"); //TODO, ta bort detta
        }

    }

    /**
     * used to put a correct guess in the user display
     * @param word
     * @param guess
     * @param amountOfLetters
     */
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

    /**
     * used to find the length of a word
     * @param word the word you want to find the amount of letters for
     * @return amountOfLetters , the number of letters is returned as an integer
     */
    private static int findCharAmountOfWord(String word){
        int amountOfLetters = 0;
        for (int i = 0; i < word.toCharArray().length; i++){
            amountOfLetters = amountOfLetters + 1;
        }
        return amountOfLetters;
    }

    /**
     * Used to give the word display the same length as the word
     * @param amountOfLetters used for the for-loop inside the method
     * @param displayWordForUser the user display
     * @param containerForWordDisplay a middleman for the displayWordForUser variable
     */
    private static void lengthOfWordDisplay(int amountOfLetters, TextView displayWordForUser, ArrayList<String> containerForWordDisplay){
        for (int i = 0; i < amountOfLetters; i++){
            containerForWordDisplay.add(" _ ");
            displayWordForUser.append(containerForWordDisplay.get(i));
        }
    }

    /**
     * This method is used to determine what the users guess means for the game.
     * @param finalWord the word the user is trying to guess the letters of, used to compare letters between itself and guess
     * @param amountOfLetters used to store the length of the word
     * @param amountOfWrongGuesses used to store the number of wrong guesses the user has committed
     * @param alreadyGuessedCharacters used to keep track of already guessed letters
     * @param wrongGuesses used to keep track of all wrong letters guessed
     * @return amountOfWrongGuesses
     */
    private static int userGuessesCharInWord(String finalWord, int amountOfLetters, int amountOfWrongGuesses, ArrayList<String> alreadyGuessedCharacters, ArrayList<String> wrongGuesses){
        //TODO, change to string
        char guess;

            guess = charInput.getText().toString().toCharArray()[0];
            charInput.setText("");
            guess = Character.toUpperCase(guess);
            finalWord = finalWord.toUpperCase();

            if(!Character.toString(guess).matches("[a-zA-Z]+")){ //checks if the users guess is a not a letter
                //TODO, add message about you have to guess a letter and not numbers and what not
                return amountOfWrongGuesses; // if its not a letter the method is ended early since anything but letters are unacceptable
            }

            if(finalWord.indexOf(guess) != -1) { // checks if the guess is a part of the word, if .indexOf() returns -1 if the guess is not a part of the word
                if (alreadyGuessedCharacters.contains(guess)){
                    //TODO, add meseage about about already guessing that char
                }
                else{
                    placeCharInWord(finalWord, guess, amountOfLetters);
                }
            }
            else{
                if (alreadyGuessedCharacters.contains(Character.toString(guess))){ //checks if character has already been guessed
                    //TODO, add meseage about about already guessing that char
                    return amountOfWrongGuesses;
                }
                else {
                    //TODO, message about wrong char + add to array of wrong chars
                        placeInWrongDisplay(amountOfWrongGuesses, wrongGuesses, guess);
                        alreadyGuessedCharacters.add(Character.toString(guess));
                        amountOfWrongGuesses = amountOfWrongGuesses + 1;
                        displayHangman(amountOfWrongGuesses);
                }
            }
        return amountOfWrongGuesses;
    }

    /**
     * This method is used to put wrong answers in an arrayList and to display them to the user
     * @param amountOfWrongGuesses when the method is used this integer goes up by one, meaning the user guessed wrong one more time
     * @param wrongGuesses this arrayList is used to store all the wrong guesses for controll of later guesses
     * @param guess the guess the user put in is stored here
     * @return
     */
    private static int placeInWrongDisplay(int amountOfWrongGuesses, ArrayList<String> wrongGuesses, char guess){
        wrongGuesses.add(Character.toString(guess));

        displayWrongAnswers.setText(displayWrongAnswers.getText()+(" "+guess));

        amountOfWrongGuesses = amountOfWrongGuesses + 1;
        return amountOfWrongGuesses;
    }

    /**
     * A method used after every wrong guess from the user. Works as a display of how many wrong guesses the user has commited.
     * @param amountOfWrongGuesses this integer is used to track which image is supposed to be shown
     */
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

    /**
     * Method launches different activities depending on the boolean winOrLose
     * @param winOrLose If the boolean is true that means the player has won the game
     */
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