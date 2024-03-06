package com.example.myapplication;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Play{

    public void applyRussianLettersFilter(EditText editText) {
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
}
