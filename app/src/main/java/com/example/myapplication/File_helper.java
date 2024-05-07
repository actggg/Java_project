package com.example.myapplication;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class File_helper {
    Context context;

    private String my_word;
    private Integer lvl_in_main;
    private Integer lvl_in_easy;
    private Integer lvl_in_medium;
    private Integer lvl_in_hard;

    public File_helper(Context context, String my_word, Integer lvl_in_main, Integer lvl_in_easy, Integer lvl_in_medium, Integer lvl_in_hard) {
        this.context = context;
        this.my_word = my_word;
        this.lvl_in_main = lvl_in_main;
        this.lvl_in_easy = lvl_in_easy;
        this.lvl_in_medium = lvl_in_medium;
        this.lvl_in_hard = lvl_in_hard;
    }



    public String file_buy_but(String clicked_button) {
        String file = "nn_words.txt";
        if (clicked_button.equals("button_easy")) {
            file = "words_easy.txt";
        }
        if (clicked_button.equals("button_medium")) {
            file = "words_medium.txt";
        }
        if (clicked_button.equals("button_hard")) {
            file = "words_hard.txt";
        }
        if (clicked_button.equals("buttonlevels")) {
            file = "go_lvls.txt";
        }
        return file;
    }

    public String work_with_file(String clicked_button) {
        String filePath = file_buy_but(clicked_button);
        Check_by_button myClass = new Check_by_button(lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
        int lvl = myClass.check_lvl(clicked_button);
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open(filePath)));

            String line;
            int lineCount = 1;
            while (lineCount <= 100) {
                line = br.readLine();
                if (lineCount == lvl) {
                    return line;
                }
                lineCount++;
            }
            return "";
        } catch (IOException e) {
            return "лампа";
        }
    }

    public boolean check_in_file(String s) {
        try {
            String filePath = "nn_words.txt";
            return isWordPresent(s, filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isWordPresent(String wordToCheck, String filePath) throws IOException {
        HashMap<String, Integer> wordMap = new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filePath)));

            String line;

            while ((line = reader.readLine()) != null) {
                wordMap.put(line.trim(), null);
            }

            reader.close();

            return wordMap.containsKey(wordToCheck);

        } catch (Exception e) {
            Log.d("TAGGGGG", e.getMessage());
            return false;
        }
    }

}