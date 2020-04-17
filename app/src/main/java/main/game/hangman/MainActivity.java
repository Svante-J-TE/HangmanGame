package main.game.hangman;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MainModel importantData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        importantData = new MainModel();
        importantData.set_answer("hej");    //Gör så här för att ge värden och för att ta värden från den andra klassen

        final Button buttonSingleplayer = findViewById(R.id.singlePlayerButton);
        buttonSingleplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.singlesetup_main);
            }
        });

        final Button buttonMultiplayer = findViewById(R.id.multiPlayerButton);
        buttonMultiplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.singlesetup_main);
            }
        });
        
        final Button buttonInstructions = findViewById(R.id.instructionsButton);
        buttonInstructions.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setContentView(R.layout.instructions_main);
            }
        });

        final Button buttonWordlist = findViewById(R.id.wordlistButton);
        buttonWordlist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setContentView(R.layout.wordlist_main);
            }
        });

        final Button buttonOptions = findViewById(R.id.optionsButton);
        buttonOptions.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setContentView(R.layout.options_main);
            }
        });

        final Button buttonExit = findViewById(R.id.shutdownButton);
        buttonExit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }


}
