package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    SharedPreferences sPref;
    public Context context;
    private Activity activity;
    private Integer lvl_in_main = 1;
    private Integer lvl_in_easy = 1;
    private Integer lvl_in_medium = 1;
    private Integer lvl_in_hard = 1;
    final String text_in_go = "lvl_in_go";
    final String text_in_easy_lvl = "lvl_in_easy_lvl";
    final String text_in_medium_lvl = "lvl_in_medium_lvl";
    final String text_in_hard_lvl = "lvl_in_hard_lvl";

    public Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        activity = this;
        loadText();
        Log.d("TAG1", "dddd");
        try {

            Menu menu = new Menu(activity, context, lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
            this.menu = menu;
            Log.d("TAG1", "e.getMessage()");
            menu.setMenu(menu);
            menu.menu();

        } catch (Exception e) {
            Log.d("TAG1", e.getMessage());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveText();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveText();
    }

    void saveText() {
        lvl_in_main = this.menu.getLvl_in_main();
        lvl_in_easy = this.menu.getLvl_in_easy();
        lvl_in_medium = this.menu.getLvl_in_medium();
        lvl_in_hard = this.menu.getLvl_in_hard();
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        //editor.putString("lvl_in_main", "1");
        //editor.putString("lvl_in_easy", "1");
        //editor.putString("lvl_in_medium", "1");
        //editor.putString("lvl_in_hard", "1");
        editor.putString(text_in_go, (lvl_in_main).toString());
        editor.putString(text_in_easy_lvl, lvl_in_easy.toString());
        editor.putString(text_in_medium_lvl, lvl_in_medium.toString());
        editor.putString(text_in_hard_lvl, lvl_in_hard.toString());
        editor.apply();
    }

    void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String a = sPref.getString(text_in_go, "1");
        String b = sPref.getString(text_in_easy_lvl, "1");
        String c = sPref.getString(text_in_medium_lvl, "1");
        String d = sPref.getString(text_in_hard_lvl, "1");
        lvl_in_main = Integer.parseInt(a);
        lvl_in_easy = Integer.parseInt(b);
        lvl_in_medium = Integer.parseInt(c);
        lvl_in_hard = Integer.parseInt(d);
    }
}
