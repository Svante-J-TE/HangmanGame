package main.game.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    private void setFinalWordForMultiplayer(){
        importantData.set_finalWord(inputFinalWord.getText().toString());
    }
}
