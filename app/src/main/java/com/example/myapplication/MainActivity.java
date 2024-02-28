package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.graphics.Color;
import android.app.AlertDialog;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    public int number_str = 1;
    public String my_word;

    SharedPreferences sPref;
    public String my_button;
    public int num = 1;
    int lvl_in_main = 1;
    int lvl_in_easy = 1;
    int lvl_in_medium = 1;
    int lvl_in_hard = 1;

    public EditText[] k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadText();
        menu();
    }

    public void menu() {
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.buttonWordOfTheDay);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word("buttonWordOfTheDay");
            }
        });
        Button buttonlevels = findViewById(R.id.buttonlevels);
        buttonlevels.setText("Прохождение уровней \n Уровень " + lvl_in_main + "/100");
        buttonlevels.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                word("buttonlevels");
            }
        });
        Button buttoneasy = findViewById(R.id.button_easy);
        buttoneasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word("button_easy");
            }
        });
        Button buttonmed = findViewById(R.id.button_medium);
        buttonmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word("button_medium");
            }
        });
        Button buttonhard = findViewById(R.id.button_hard);
        buttonhard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word("button_hard");
            }
        });
        Button buttonfriend = findViewById(R.id.buttonWordOfTheDay);
        buttonhard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveText();
            }
        });
    }

    public void word(String clicked_button) {
        setContentView(R.layout.word);
        TextView tx = findViewById(R.id.wordli_text);
        tx.setText("Уровень № " + check_lvl(clicked_button));
        my_button = clicked_button;
        my_word = work_with_file(clicked_button);
        number_str = 1;
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                EditText editText = findViewById(getResources().getIdentifier("letter_input" + i + "_" + j, "id", getPackageName()));
                applyRussianLettersFilter(editText);
            }
        }
        block(number_str);
        k = new EditText[]{findViewById(R.id.letter_input1_1), findViewById(R.id.letter_input1_2), findViewById(R.id.letter_input1_3), findViewById(R.id.letter_input1_4), findViewById(R.id.letter_input1_5),
                findViewById(R.id.letter_input2_1), findViewById(R.id.letter_input2_2), findViewById(R.id.letter_input2_3), findViewById(R.id.letter_input2_4), findViewById(R.id.letter_input2_5),
                findViewById(R.id.letter_input3_1), findViewById(R.id.letter_input3_2), findViewById(R.id.letter_input3_3), findViewById(R.id.letter_input3_4), findViewById(R.id.letter_input3_5),
                findViewById(R.id.letter_input4_1), findViewById(R.id.letter_input4_2), findViewById(R.id.letter_input4_3), findViewById(R.id.letter_input4_4), findViewById(R.id.letter_input4_5),
                findViewById(R.id.letter_input5_1), findViewById(R.id.letter_input5_2), findViewById(R.id.letter_input5_3), findViewById(R.id.letter_input5_4), findViewById(R.id.letter_input5_5),};

        ImageButton back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu();
            }
        });
        Button check_button = findViewById(R.id.check_button);
        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(number_str, clicked_button);
            }
        });
    }

    public void dialog(String text){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(text)
            .setPositiveButton("OK", null)
                    .show();
    menu();}

    public void trueWord(int num, String clicked_button) {
        String str = "";
        for (int j = 1; j <= 5; j++) {
            EditText editText = findViewById(getResources().getIdentifier("letter_input" + num + "_" + j, "id", getPackageName()));
            str += editText.getText().toString();
        }
        if (str.equals(my_word)) {
            dialog("Всё верно!!!");
            next_lvl(clicked_button);
            menu();
        }
        else if (num == 5){
            number_str = 1;
            dialog("Вы ошиблись!!!");
            menu();
        }
    }

    public void block(int num) {
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                EditText editText = findViewById(getResources().getIdentifier("letter_input" + i + "_" + j, "id", getPackageName()));
                if (i != num) {
                    editText.setEnabled(false);
                } else {
                    editText.setEnabled(true);
                }
            }
        }
    }

    public void check(int num, String clicked_button) {
        int empty = 0;
        for (int i = 0; i < 5; i++) {
            if (k[i + (num - 1) * 5].getText().toString().isEmpty()) {
                empty = 1;
            }
        }
        String str = "";
        for (int j = 1; j <= 5; j++) {
            EditText editText = findViewById(getResources().getIdentifier("letter_input" + num + "_" + j, "id", getPackageName()));
            str += editText.getText().toString();
        }
        if (check_in_file(str)) {
            if (empty == 0) {
                for (int i = 0; i < 5; i++) {
                    int t = i + (num - 1) * 5;
                    if ((checkLetterInWord(my_word, k[t].getText().toString()))) {
                        k[t].setBackgroundColor(Color.YELLOW);
                    }
                    if ((isLetterAtIndex(my_word, k[t].getText().toString(), i))) {
                        k[t].setBackgroundColor(Color.GREEN);
                    }
                }
                number_str++;
                block(number_str);
                trueWord(num, clicked_button);
            }
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Такого слова не существует")
                    .setPositiveButton("OK", null)
                    .show();
        }
    }

    public boolean checkLetterInWord(String word, String letter) {
        word = word.toLowerCase();

        return word.contains(letter);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }

    void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("lvl_in_main", String.valueOf(lvl_in_main));
        editor.putString("lvl_in_easy", String.valueOf(lvl_in_easy));
        editor.putString("lvl_in_medium", String.valueOf(lvl_in_medium));
        editor.putString("lvl_in_hard", String.valueOf(lvl_in_hard));
        editor.apply();
    }

    void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        lvl_in_main = Integer.parseInt(sPref.getString("lvl_in_main", "2"));
        lvl_in_easy = Integer.parseInt(sPref.getString("lvl_in_easy", "2"));
        lvl_in_medium = Integer.parseInt(sPref.getString("lvl_in_medium", "2"));
        lvl_in_hard = Integer.parseInt(sPref.getString("lvl_in_hard", "2"));
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





    public String work_with_file(String clicked_button) {
        String filePath = file_buy_but(clicked_button);
        int lvl = check_lvl(clicked_button);
        // Путь к папке Assets
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open(filePath)));

            String line;
            int lineCount = 1;
            while (lineCount <= 100) {
                line = br.readLine();
                // Если номер строки совпадает с заданным, возвращаем ее
                if (lineCount == lvl) {
                    return line;
                }
                lineCount++;
            }
            return "";
        }
        catch (IOException e){
            return "лампа";
        }
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

    private void applyRussianLettersFilter(EditText editText) {
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    EditText previousEditText = (EditText) editText.focusSearch(View.FOCUS_LEFT);
                    if (previousEditText != null) {
                        previousEditText.requestFocus();
                        return true;
                    }
                }
                return false;
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                if (!input.matches("[а-яА-Я]*")) {
                    s.replace(0, s.length(), getRussianLettersFromString(input));
                } else if (input.length() > 0) {
                    moveToNextField(editText);
                }
            }
        });
    }


    private void moveToNextField(EditText currentEditText) {
        View next = currentEditText.focusSearch(View.FOCUS_RIGHT);
        if (next != null) {
            next.requestFocus();
        }
    }

    private String getRussianLettersFromString(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CYRILLIC) {
                result.append(c);
            }
        }
        return result.toString();
    }


    public boolean check_in_file(String s) {
        try {
            String filePath = "nn_words.txt";

            if (isWordPresent(s, filePath)) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isWordPresent(String wordToCheck, String filePath) throws IOException {
        HashMap<String, Integer> wordMap = new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(filePath), "UTF-8"));

            String line;

            while ((line = reader.readLine()) != null) {
                wordMap.put(line.trim(), null);
            }

            reader.close();

            return wordMap.containsKey(wordToCheck);

        } catch (Exception e) {
            return false;
        }
    }
}
