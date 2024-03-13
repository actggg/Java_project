package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import android.content.ClipData;
import android.content.ClipboardManager;


public class Dialog {
    private static Activity activity;
    public String my_word;
    public Integer number_str;
    private Integer lvl_in_main;
    private Integer lvl_in_easy;
    private Integer lvl_in_medium;
    private Integer lvl_in_hard;
    public String word;
    public String code = "ааааа";
    public Menu menu;
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

    public Dialog(Activity _activity, Context context, Menu menu, Integer lvl_in_main, Integer lvl_in_easy, Integer lvl_in_medium, Integer lvl_in_hard){
        this.activity = _activity;
        this.context = context;
        this.menu = menu;
        this.lvl_in_main = lvl_in_main;
        this.lvl_in_easy = lvl_in_easy;
        this.lvl_in_medium = lvl_in_medium;
        this.lvl_in_hard = lvl_in_hard;
    }

    public String getCode(){
        return this.code;
    }

    public void setWord(String word_input){
        this.word = word_input;
    }
    public int trueWord(int num, String clicked_button, String my_str) {
        if (my_str.equals(my_word)) {
            //dialog("Всё верно!!!");
            if (clicked_button != "PASS") {
                Check_by_button ch_bt = new Check_by_button(lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
                ch_bt.next_lvl(clicked_button);
                lvl_in_main = ch_bt.getLvl_in_main();
                lvl_in_easy = ch_bt.getLvl_in_easy();
                lvl_in_medium = ch_bt.getLvl_in_medium();
                lvl_in_hard = ch_bt.getLvl_in_hard();
            }
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
                .setPositiveButton("ОК", null)
                .show();
    }

    public void showChooseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Выберите действие:");
        builder.setPositiveButton("Загадать слово для друга", (dialog, which) -> showDialog());
        builder.setNegativeButton("Ввести код друга", (dialog, which) -> enterFriendCode());

        builder.show();
    }

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Введите слово из 5 букв:");
        final EditText input = new EditText(context);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            word = input.getText().toString();
            if (word.length() == 5) {
                showMessage("Спасибо, вы ввели слово: " + word);
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", AlphabetConverter.cypher_word(word).toString());
                clipboard.setPrimaryClip(clip);
            } else {
                showMessage("Пожалуйста, введите слово из 5 букв!");
                showDialog();
            }
        });

        builder.setNegativeButton("Отмена", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    public void enterFriendCode() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Введите шифр друга:");

        final EditText input = new EditText(context);
        builder.setView(input);

        builder.setPositiveButton("ОК", (dialog, which) -> {
            String friendCode = input.getText().toString();
            showMessage("Вы ввели шифр друга: " + friendCode);
            friendCode = AlphabetConverter.anti_cypher(Long.parseLong(friendCode));
            Word word1 = new Word(activity, context, menu, lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
            word1.word_my_word(friendCode);
        });

        builder.setNegativeButton("Отмена", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    public void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}
