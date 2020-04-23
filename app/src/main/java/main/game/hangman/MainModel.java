package main.game.hangman;

import java.util.ArrayList;

public class MainModel {

    private String _answer;

    public String get_answer() { return _answer; }
    public void set_answer(String _answer) { this._answer = _answer; }


    private int _volume;

    public int get_volume() { return _volume; }
    public void set_volume(int _volume) { this._volume = _volume; }


    private int _difficulty;

    public int get_difficulty(){ return _difficulty; }
    public void set_difficulty(int _difficulty){ this._difficulty = _difficulty; }


    private ArrayList<String> wordList = new ArrayList<String>();




}
