package main.game.hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MainModel {

    private String _answer;

    public String get_answer() { return _answer; }
    public void set_answer(String _answer) { this._answer = _answer; }


    private String _finalWord = "heej";

    public String get_finalWord() { return _finalWord; }
    public void set_finalWord(String _finalWord) { this._finalWord = _finalWord; }


    private int _volume;

    public int get_volume() { return _volume; }
    public void set_volume(int _volume) { this._volume = _volume; }


    private int _difficulty;

    public int get_difficulty(){ return _difficulty; }
    public void set_difficulty(int _difficulty){ this._difficulty = _difficulty; }


    private ArrayList<String> _wordList = new ArrayList<String>();

    public ArrayList<String> get_wordList() { return _wordList; }
    public void set_wordList(ArrayList<String> _wordList) { this._wordList = _wordList; }
    public void wordListCreator(){
        try{
            FileReader file = new FileReader("main/game/hangman/wordListEng");
            Scanner reader = new Scanner(file);
            while (reader.hasNext()){
                _wordList.add(reader.nextLine().trim());
            }
        }
        catch (FileNotFoundException test){
            System.out.println("Something went wrong");
        }
    }

}