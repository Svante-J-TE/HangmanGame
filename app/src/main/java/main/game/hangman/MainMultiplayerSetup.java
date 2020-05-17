package main.game.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainMultiplayerSetup extends AppCompatActivity {

    private MainModel importantData;
    private static EditText inputFinalWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplayersetup_main);

        importantData = new MainModel();
        inputFinalWord = (EditText) findViewById(R.id.finalWordInput);

        importantData.set_gameMode(2);

        final Button buttonSubmit = findViewById(R.id.submitFinalWord);
        buttonSubmit.setEnabled(false);

        toggleButtonUsability(buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFinalWordForMultiplayer();
                Intent intent = new Intent(MainMultiplayerSetup.this, MainGame.class);
                startActivity(intent);
            }
        });

        final Button buttonGoBack = findViewById(R.id.returnButton);
        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFinalWordForMultiplayer();
                finish();
            }
        });
    }

    /**
     * sets user input to the finalWord
     */
    private void setFinalWordForMultiplayer(){
        importantData.set_finalWord(inputFinalWord.getText().toString());
    }

    /**
     * toggles the ability to use the submit button. Used to prevent guess from = null
     * @param buttonSubmit
     */
    private void toggleButtonUsability(final Button buttonSubmit){
        inputFinalWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                buttonSubmit.setEnabled(!inputFinalWord.getText().toString().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}
