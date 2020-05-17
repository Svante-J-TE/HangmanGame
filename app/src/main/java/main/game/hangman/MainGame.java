package main.game.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainGame extends AppCompatActivity {

    private String finalWord = "";
    private int amountOfGuesses = 0;
    private static ImageView hangmanDisplay;
    private static EditText charInput;
    private static TextView displayWordForUser;
    private static TextView displayWrongAnswers;
    private static TextView displayGuessesLeft;
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
        displayGuessesLeft = (TextView) findViewById(R.id.amountOfGuessesLeftDisplay);

        hangmanDisplay.setImageResource(R.drawable.hangman_default);


        finalWordDependOnGameMode();

        finalWord = importantData.get_finalWord();

        lengthOfWordDisplay(finalWord.length(), displayWordForUser, containerForWordDisplay);


        final Button buttonSubmit = findViewById(R.id.submitButton);
        buttonSubmit.setEnabled(false);

        toggleButtonUsability(buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amountOfGuesses = userGuessesCharInWord(finalWord, finalWord.length(), amountOfGuesses, alreadyGuessedCharacters, wrongGuesses);

                if (containerForWordDisplay.contains(" _ ") == false) {
                    chancesLeft = true;
                    clearDisplaysAndVariables();
                    launchPlayerWinOrLoseActivity(chancesLeft);
                }
                if (amountOfGuesses == 11) {
                    chancesLeft = false;
                    clearDisplaysAndVariables();
                    launchPlayerWinOrLoseActivity(chancesLeft);
                }

            }
        });

    }

    /**
     * toggles the ability to use the submit button. Used to prevent guess from = null
     * @param buttonSubmit
     */
    private void toggleButtonUsability(final Button buttonSubmit){
        charInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                buttonSubmit.setEnabled(!charInput.getText().toString().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    /**
     * a method that is used to see if its multipllayer or single player. case 1 , is singleplayer
     * case 2 , multiplayer
     */
    private void finalWordDependOnGameMode(){
        switch (importantData.get_gameMode()){
            case 1:
                randomizeFinalWord();
            break;
            case 2:
                //finalword is already set in MainMultiplayerSetup.java
        }
    }

    /**
     * A method used to randomize a word from a wordList
     */
    private static void randomizeFinalWord(){
            Random random = new Random();
            boolean acceptableWord = false;
            while (acceptableWord == false) {
                int index = random.nextInt(importantData.get_wordList().size());
                importantData.set_finalWord(importantData.get_wordList().get(index));
                if (importantData.get_difficulty() == 1){
                    if (importantData.get_finalWord().length() < 4){
                        acceptableWord = true;
                    }
                } else if (importantData.get_difficulty() == 2){
                    if (importantData.get_finalWord().length() < 8 && importantData.get_finalWord().length() > 3){
                        acceptableWord = true;
                    }
                } else if (importantData.get_difficulty() == 3){
                    if (importantData.get_finalWord().length() > 7){
                        acceptableWord = true;
                    }
                }
            }

    }

    /**
     * used to put a correct guess in the user display
     * @param word
     * @param guess
     * @param amountOfLetters
     */
    private static void placeCharInWord(String word, char guess, int amountOfLetters){
        for (int i = 0; i < word.length(); i++){
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
     * method for showing user the amount of mistakes he/she can make before losing
     * @param amountOfGuesses
     */
    private static void placeAmountOfGuessesInDisplay(int amountOfGuesses){
        int guessesLeft = 11 - amountOfGuesses;
        displayGuessesLeft.setText("Amount of misstakes left: " + guessesLeft);

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
    private int userGuessesCharInWord(String finalWord, int amountOfLetters, int amountOfWrongGuesses, ArrayList<String> alreadyGuessedCharacters, ArrayList<String> wrongGuesses){
        char guess;

            guess = charInput.getText().toString().toCharArray()[0];
            charInput.setText("");
            guess = Character.toUpperCase(guess);
            finalWord = finalWord.toUpperCase();

            if(!Character.toString(guess).matches("[a-zA-Z]+")){ //checks if the users guess is a not a letter
                launchInvalidGuessActivity();
                return amountOfWrongGuesses; // if its not a letter the method is ended early since anything but letters are unacceptable
            }

            if(finalWord.indexOf(guess) != -1) { // checks if the guess is a part of the word, if .indexOf() returns -1 if the guess is not a part of the word
                if (alreadyGuessedCharacters.contains(guess)){
                    launchInvalidGuessActivity();
                }
                else{
                    placeCharInWord(finalWord, guess, amountOfLetters);
                }
            }
            else{
                if (alreadyGuessedCharacters.contains(Character.toString(guess))){ //checks if character has already been guessed
                    launchInvalidGuessActivity();
                    return amountOfWrongGuesses;
                }
                else {
                    placeInWrongDisplay(amountOfWrongGuesses, wrongGuesses, guess);
                    alreadyGuessedCharacters.add(Character.toString(guess));
                    amountOfWrongGuesses = amountOfWrongGuesses + 1;
                    placeAmountOfGuessesInDisplay(amountOfWrongGuesses);
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
        switch (amountOfWrongGuesses){
            case 1:
                hangmanDisplay.setImageResource(R.drawable.hangman_image1);
                break;
            case 2:
                hangmanDisplay.setImageResource(R.drawable.hangman_image2);
                break;
            case 3:
                hangmanDisplay.setImageResource(R.drawable.hangman_image3);
                break;
            case 4:
                hangmanDisplay.setImageResource(R.drawable.hangman_image4);
                break;
            case 5:
                hangmanDisplay.setImageResource(R.drawable.hangman_image5);
                break;
            case 6:
                hangmanDisplay.setImageResource(R.drawable.hangman_image6);
                break;
            case 7:
                hangmanDisplay.setImageResource(R.drawable.hangman_image7);
                break;
            case 8:
                hangmanDisplay.setImageResource(R.drawable.hangman_image8);
                break;
            case 9:
                hangmanDisplay.setImageResource(R.drawable.hangman_image9);
                break;
            case 10:
                hangmanDisplay.setImageResource(R.drawable.hangman_image10);
                break;
            case 11:
                hangmanDisplay.setImageResource(R.drawable.hangman_image11);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + amountOfWrongGuesses);
        }
    }

    /**
     * Clears all guesses both from the display and from the logistics for when the user decides to play again
     */
    private static void clearDisplaysAndVariables(){
        displayWordForUser.setText("");
        displayWrongAnswers.setText("Wrong answers: ");
        alreadyGuessedCharacters.clear();
        containerForWordDisplay.clear();
        wrongGuesses.clear();
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

    /**
     * launches activity for invalid inputs
     */
    private void launchInvalidGuessActivity(){
        Intent intent = new Intent(this, MainInvalidGuess.class);
        startActivity(intent);
    }

}