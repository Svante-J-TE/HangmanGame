package main.game.hangman;

import java.util.ArrayList;

public class MainModel {

    private static String _finalWord;

    public String get_finalWord() { return _finalWord; }
    public void set_finalWord(String finalWord) { this._finalWord = finalWord; }


    private static int _gameMode;

    public int get_gameMode() { return _gameMode; }
    public void set_gameMode(int _gameMode) { this._gameMode = _gameMode; }


    private static int _difficulty;

    public int get_difficulty(){ return _difficulty; }
    public void set_difficulty(int difficulty){ _difficulty = difficulty; }


    private static ArrayList<String> _wordList = new ArrayList<String>();

    public ArrayList<String> get_wordList() { return _wordList; }
    public void set_wordList(String word) { _wordList.add(word); }

}