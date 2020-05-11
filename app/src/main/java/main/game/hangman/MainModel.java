package main.game.hangman;

import android.app.Application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MainModel {

    private static String _finalWord;

    public String get_finalWord() { return _finalWord; }
    public void set_finalWord(String _finalWord) { this._finalWord = _finalWord; }


    private int _volume;

    public int get_volume() { return _volume; }
    public void set_volume(int _volume) { this._volume = _volume; }


    private static int _difficulty;

    public int get_difficulty(){ return _difficulty; }
    public void set_difficulty(int difficulty){ _difficulty = difficulty; }


    private static ArrayList<String> _wordList = new ArrayList<String>();

    public ArrayList<String> get_wordList() { return _wordList; }
    public void set_wordList(String word) { _wordList.add(word); }

}