package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Menu{
    private static Activity activity;
    private Integer lvl_in_main;
    private Integer lvl_in_easy;
    private Integer lvl_in_medium;
    private Integer lvl_in_hard;
    private Context context;
    public Menu menu1;

    public Menu( Activity _activity, Context context, Integer lvl_in_main, Integer lvl_in_easy, Integer lvl_in_medium, Integer lvl_in_hard){
        this.activity = _activity;
        this.context = context;
        this.lvl_in_main = lvl_in_main;
        this.lvl_in_easy = lvl_in_easy;
        this.lvl_in_medium = lvl_in_medium;
        this.lvl_in_hard = lvl_in_hard;
    }

    public void setMenu(Menu menu){
        this.menu1 = menu;
    }

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

    public void setLvl_in_main(int lvl) {
        this.lvl_in_main = lvl;
    }

    public void setLvl_in_easy(int lvl) {
        this.lvl_in_easy = lvl;
    }
    public void setLvl_in_medium(int lvl) {
        this.lvl_in_medium = lvl;
    }
    public void setLvl_in_hard(int lvl) {
        this.lvl_in_hard = lvl;
    }

    public void menu() {
        activity.setContentView(R.layout.activity_main);
        try {

            Word word = new Word(activity, context, menu1, lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
            Button buttonlevels = activity.findViewById(R.id.buttonlevels);
            buttonlevels.setText("Прохождение уровней \n Уровень " + lvl_in_main + "/100");
            buttonlevels.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    word.word("buttonlevels");
                }
            });
            Button buttoneasy = activity.findViewById(R.id.button_easy);
            buttoneasy.setText("Легкий уровень " + lvl_in_easy + "/100");
            buttoneasy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    word.word("button_easy");
                }
            });
            Button buttonmed = activity.findViewById(R.id.button_medium);
            buttonmed.setText("Средний уровень " + lvl_in_medium + "/100");
            buttonmed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    word.word("button_medium");
                }
            });
            Button buttonhard = activity.findViewById(R.id.button_hard);
            buttonhard.setText("Сложный уровень " + lvl_in_hard + "/100");
            buttonhard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    word.word("button_hard");
                }
            });
            Button buttonfriend = activity.findViewById(R.id.buttonFriendWord);
            buttonfriend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(activity, context, menu1, lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
                    dialog.showChooseDialog();

                }
            });
        }
        catch (Exception e){
            Log.d("TAGGG", e.getMessage());
        }
    }
}