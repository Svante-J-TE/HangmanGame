package main.game.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainWordList extends AppCompatActivity {

    private TextView wordListDisplay;
    private MainModel importantData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list_main);

        importantData = new MainModel();
        wordListDisplay = (TextView)findViewById(R.id.displayWordlist);

        populateWordlistTextView();

        final Button buttonReturn = findViewById(R.id.returnButton);
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * populates the textView with content from the wordlist
     */
    private void populateWordlistTextView(){
        String container;
        for (int i = 0; i < importantData.get_wordList().size(); i++){
            container = importantData.get_wordList().get(i);
            wordListDisplay.append(container+"\n");
        }
    }
}
