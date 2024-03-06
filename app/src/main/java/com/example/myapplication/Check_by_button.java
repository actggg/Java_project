package com.example.myapplication;

import android.app.Activity;

public class Check_by_button {


    private Integer lvl_in_main;
    private Integer lvl_in_easy;
    private Integer lvl_in_medium;
    private Integer lvl_in_hard;

    public Integer getLvl_in_main() {
        return lvl_in_main;
    }

    public Integer getLvl_in_easy() {
        return lvl_in_easy;
    }
    public Integer getLvl_in_medium() {
        return lvl_in_medium;
    }
    public Integer getLvl_in_hard() {
        return lvl_in_hard;
    }

    public Check_by_button(Integer lvl_in_main, Integer lvl_in_easy, Integer lvl_in_medium, Integer lvl_in_hard){
        this.lvl_in_main = lvl_in_main;
        this.lvl_in_easy = lvl_in_easy;
        this.lvl_in_medium = lvl_in_medium;
        this.lvl_in_hard = lvl_in_hard;
    }


    public void next_lvl(String clicked_button) {
        if (clicked_button == "button_easy"){
            lvl_in_easy++;
        }
        if (clicked_button == "button_medium"){
            lvl_in_medium++;
        }
        if (clicked_button == "button_hard"){
            lvl_in_hard++;
        }
        if (clicked_button == "buttonlevels"){
            lvl_in_main++;
        }
    }

    public String file_buy_but(String clicked_button) {
        String file = "nn_words.txt";
        if (clicked_button == "button_easy"){
            file = "words_easy.txt";
        }
        if (clicked_button == "button_medium"){
            file = "words_medium.txt";
        }
        if (clicked_button == "button_hard"){
            file = "words_hard.txt";
        }
        if (clicked_button == "buttonlevels"){
            file = "go_lvls.txt";
        }
        return file;
    }
    public int check_lvl(String clicked_button){
        if (clicked_button == "button_easy"){
            return lvl_in_easy;
        }
        if (clicked_button == "button_medium"){
            return lvl_in_medium;
        }
        if (clicked_button == "button_hard"){
            return lvl_in_hard;
        }
        if (clicked_button == "buttonlevels"){
            return lvl_in_main;
        }
        return 0;
    }
}
