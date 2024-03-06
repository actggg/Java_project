package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.app.AlertDialog;

public class Dialog {
    private static Activity activity;
    public String my_word;
    public Integer number_str;
    private Integer lvl_in_main;
    private Integer lvl_in_easy;
    private Integer lvl_in_medium;
    private Integer lvl_in_hard;
    private Context context;
    public Dialog( Activity _activity, Context context, String my_word, Integer lvl_in_main, Integer lvl_in_easy, Integer lvl_in_medium, Integer lvl_in_hard){
        this.activity = _activity;
        this.my_word = my_word;
        this.context = context;
        this.lvl_in_main = lvl_in_main;
        this.lvl_in_easy = lvl_in_easy;
        this.lvl_in_medium = lvl_in_medium;
        this.lvl_in_hard = lvl_in_hard;
    }
    public int trueWord(int num, String clicked_button, String my_str) {
        if (my_str.equals(my_word)) {
            //dialog("Всё верно!!!");
            Check_by_button ch_bt = new Check_by_button(lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
            ch_bt.next_lvl(clicked_button);
            lvl_in_main =ch_bt.getLvl_in_main();
            lvl_in_easy =ch_bt.getLvl_in_easy();
            lvl_in_medium =ch_bt.getLvl_in_medium();
            lvl_in_hard =ch_bt.getLvl_in_hard();
            return 1;
        }
        else if (num == 5){
            number_str = 1;
            return 2;
            //dialog("Вы ошиблись!!!");
        }
        return 3;
    }

    public void dialog(String text){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(text)
                .setPositiveButton("OK", null)
                .show();
    }
}
