package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.graphics.Color;
import android.app.AlertDialog;
import android.content.Context;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    public String my_word = "лампа";
    public int number_str = 1;

    // Переменная для хранения названий 25 полей ввода
    public EditText[] k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.textViewTitle);
        tv.setTextAppearance(this, R.style.CustomButtonFont);
        //Button b = findViewById(R.id.buttonSubmit);
        //b.setOnClickListener(new View.OnClickListener() {
          //  @Override
          //  public void onClick(View v) {
           //     word();
           // }
       // });
    }

    public void word(){
        setContentView(R.layout.word);

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
        // Заполнение массива с названиями полей ввода
        Button button = findViewById(R.id.check_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(number_str);
            }
        });
    }
    public void trueWord(int num, String text, boolean triger) {
        String str = "";
        for (int j = 1; j <= 5; j++) {
            EditText editText = findViewById(getResources().getIdentifier("letter_input" + num + "_" + j, "id", getPackageName()));
            str += editText.getText().toString();
        }
        if (str.equals(my_word) || !triger) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(text)
                    .setPositiveButton("OK", null)
                    .show();
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

    public void check(int num) {
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
                if (number_str <= 5) {
                    block(number_str);
                    trueWord(num, "Все верно!!!", true);
                } else if (number_str == 6) {
                    trueWord(num, "Все верно!!!", true);
                } else {
                    trueWord(num, "К сожалению вы ошиблись", false);
                    number_str = 1;
                    block(number_str);
                }
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
                } else {
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
            String filePath = "nn_words.txt"; // Имя файла в папке assets

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
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(filePath)));
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
