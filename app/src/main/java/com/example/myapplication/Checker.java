package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.EditText;
import android.app.AlertDialog;

public class Checker {
    private Activity activity;
    private Integer lvl_in_main;
    private Integer lvl_in_easy;
    private Integer lvl_in_medium;
    private Integer lvl_in_hard;
    private Integer number_str;
    private String my_word;
    private Context context;

    public Checker(Activity _activity, Context context, String my_word, Integer lvl_in_main, Integer lvl_in_easy, Integer lvl_in_medium, Integer lvl_in_hard) {
        this.activity = _activity;
        this.context = context;
        this.my_word = my_word;
        this.lvl_in_main = lvl_in_main;
        this.lvl_in_easy = lvl_in_easy;
        this.lvl_in_medium = lvl_in_medium;
        this.lvl_in_hard = lvl_in_hard;
    }

    public int check(Integer number_str, String clicked_button, EditText[] inputs) {
        Log.d("TAG2", "str");
        this.number_str = number_str;
        int empty = 0;
        for (int i = 0; i < 5; i++) {
            if (inputs[i + (number_str - 1) * 5].getText().toString().isEmpty()) {
                empty = 1;
            }
        }
        String str = "";
        for (int j = 1; j <= 5; j++) {
            EditText editText = activity.findViewById(activity.getResources().getIdentifier("letter_input" + number_str + "_" + j, "id", activity.getPackageName()));
            str += editText.getText().toString();
        }
        Log.d("TAG2", "str");
        File_helper fl = new File_helper(context, my_word, lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
        if (fl.check_in_file(str)) {
            if (empty == 0) {
                String my_str = "";
                for (int i = 0; i < 5; i++) {
                    int t = i + (this.number_str - 1) * 5;
                    if ((checkLetterInWord(my_word, inputs[t].getText().toString()))) {
                        inputs[t].setBackgroundColor(Color.YELLOW);
                    }
                    if ((isLetterAtIndex(my_word, inputs[t].getText().toString(), i))) {
                        inputs[t].setBackgroundColor(Color.GREEN);
                        my_str += inputs[t].getText();
                    }
                }
                this.number_str++;

                Word.block(number_str);

                Dialog d = new Dialog(activity, context, my_word, lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
                return (d.trueWord(number_str, clicked_button, my_str));
            }}
        return 4;
    }
        /*
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Такого слова не существует")
                    .setPositiveButton("OK", null)
                    .show();
            return 4;

         */


    //}
    //}

    public Integer getNumber_str() {
        return this.number_str;
    }

    public boolean checkLetterInWord(String word, String letter) {
        word = word.toLowerCase();

        return word.contains(letter);
    }

    public boolean isLetterAtIndex(String word, String letter, int index) {
        if (index >= 0 && index < word.length()) {
            char charAtIndex = word.charAt(index);
            if (Character.isLetter(charAtIndex) && String.valueOf(charAtIndex).equalsIgnoreCase(letter)) {
                return true;
            }
        }

        return false;
    }

}