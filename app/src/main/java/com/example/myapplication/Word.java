package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Word {
    private static Activity activity;
    private String my_button;
    private Integer lvl_in_main;
    private Integer lvl_in_easy;
    private Integer lvl_in_medium;
    private Integer lvl_in_hard;
    private Context context;
    private String my_word;
    private EditText[] inputs;
    private Integer number_str;
    public Menu menu1;

    public Word(Activity _activity, Context context, Menu menu, Integer lvl_in_main, Integer lvl_in_easy, Integer lvl_in_medium, Integer lvl_in_hard) {
        this.activity = _activity;
        this.context = context;
        this.menu1 = menu;
        this.lvl_in_main = lvl_in_main;
        this.lvl_in_easy = lvl_in_easy;
        this.lvl_in_medium = lvl_in_medium;
        this.lvl_in_hard = lvl_in_hard;
    }


    public static void block(Integer num) {
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                EditText editText = activity.findViewById(activity.getResources().getIdentifier("letter_input" + i + "_" + j, "id", activity.getPackageName()));
                if (num != i) {
                    editText.setEnabled(false);
                } else {
                    editText.setEnabled(true);
                }
            }
        }
    }

    public void word_my_word(String my_word) {
        Log.d("TEG1", my_word);
        Log.d("TEG1", "my_word");
        activity.setContentView(R.layout.word);
        this.number_str = 1;
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                EditText editText = activity.findViewById(activity.getResources().getIdentifier("letter_input" + i + "_" + j, "id", activity.getPackageName()));
                Play play = new Play();
                play.applyRussianLettersFilter(editText);
            }
        }
        block(number_str);
        Log.d("TEG1", "my_word");
        inputs = new EditText[]{activity.findViewById(R.id.letter_input1_1), activity.findViewById(R.id.letter_input1_2), activity.findViewById(R.id.letter_input1_3), activity.findViewById(R.id.letter_input1_4), activity.findViewById(R.id.letter_input1_5),
                activity.findViewById(R.id.letter_input2_1), activity.findViewById(R.id.letter_input2_2), activity.findViewById(R.id.letter_input2_3), activity.findViewById(R.id.letter_input2_4), activity.findViewById(R.id.letter_input2_5),
                activity.findViewById(R.id.letter_input3_1), activity.findViewById(R.id.letter_input3_2), activity.findViewById(R.id.letter_input3_3), activity.findViewById(R.id.letter_input3_4), activity.findViewById(R.id.letter_input3_5),
                activity.findViewById(R.id.letter_input4_1), activity.findViewById(R.id.letter_input4_2), activity.findViewById(R.id.letter_input4_3), activity.findViewById(R.id.letter_input4_4), activity.findViewById(R.id.letter_input4_5),
                activity.findViewById(R.id.letter_input5_1), activity.findViewById(R.id.letter_input5_2), activity.findViewById(R.id.letter_input5_3), activity.findViewById(R.id.letter_input5_4), activity.findViewById(R.id.letter_input5_5),};
        ImageButton back_button = activity.findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu1.menu();
            }
        });
        Button check_button = activity.findViewById(R.id.check_button);
        String clicked_button = "PASS";
        Log.d("TEG1", "my_word");
        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checker checker = new Checker(activity, context, my_word, lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
                if (checker.check(number_str, clicked_button, inputs) == 1) {
                    menu1.menu();
                    Dialog dialog = new Dialog(activity, context, my_word, lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
                    dialog.dialog("Все верно!!!");
                } else if (checker.check(number_str, clicked_button, inputs) == 2) {
                    menu1.menu();
                    Dialog dialog = new Dialog(activity, context, my_word, lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
                    dialog.dialog("Вы ошиблись, верное слово: " + my_word);
                } else if (checker.check(number_str, clicked_button, inputs) == 3) {
                    Log.d("TEG1", "3");
                    number_str = checker.getNumber_str();
                    block(number_str);
                } else {
                    Dialog dialog = new Dialog(activity, context, my_word, lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
                    dialog.dialog("Такого слова не существует!!!");
                }

            }
        });
    }

    public void word(String clicked_button) {
        activity.setContentView(R.layout.word);
        TextView tx = activity.findViewById(R.id.wordli_text);
        Check_by_button myClass = new Check_by_button(lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
        tx.setText("Уровень № " + myClass.check_lvl(clicked_button));
        my_button = clicked_button;
        File_helper fh = new File_helper(context, my_word, lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
        my_word = fh.work_with_file(clicked_button);
        this.number_str = 1;
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                EditText editText = activity.findViewById(activity.getResources().getIdentifier("letter_input" + i + "_" + j, "id", activity.getPackageName()));
                Play play = new Play();
                play.applyRussianLettersFilter(editText);
            }
        }
        block(number_str);
        inputs = new EditText[]{activity.findViewById(R.id.letter_input1_1), activity.findViewById(R.id.letter_input1_2), activity.findViewById(R.id.letter_input1_3), activity.findViewById(R.id.letter_input1_4), activity.findViewById(R.id.letter_input1_5),
                activity.findViewById(R.id.letter_input2_1), activity.findViewById(R.id.letter_input2_2), activity.findViewById(R.id.letter_input2_3), activity.findViewById(R.id.letter_input2_4), activity.findViewById(R.id.letter_input2_5),
                activity.findViewById(R.id.letter_input3_1), activity.findViewById(R.id.letter_input3_2), activity.findViewById(R.id.letter_input3_3), activity.findViewById(R.id.letter_input3_4), activity.findViewById(R.id.letter_input3_5),
                activity.findViewById(R.id.letter_input4_1), activity.findViewById(R.id.letter_input4_2), activity.findViewById(R.id.letter_input4_3), activity.findViewById(R.id.letter_input4_4), activity.findViewById(R.id.letter_input4_5),
                activity.findViewById(R.id.letter_input5_1), activity.findViewById(R.id.letter_input5_2), activity.findViewById(R.id.letter_input5_3), activity.findViewById(R.id.letter_input5_4), activity.findViewById(R.id.letter_input5_5),};
        ImageButton back_button = activity.findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_and_menu(lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
            }
        });
        Button check_button = activity.findViewById(R.id.check_button);
        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checker checker = new Checker(activity, context, my_word, lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
                if (checker.check(number_str, clicked_button, inputs) == 1) {
                    Check_by_button cbb = new Check_by_button(lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
                    cbb.next_lvl(clicked_button);
                    lvl_in_main = cbb.getLvl_in_main();
                    lvl_in_easy = cbb.getLvl_in_easy();
                    lvl_in_medium = cbb.getLvl_in_medium();
                    lvl_in_hard = cbb.getLvl_in_hard();
                    set_and_menu(lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
                    Dialog dialog = new Dialog(activity, context, my_word, lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
                    dialog.dialog("Все верно!!!");
                } else if (checker.check(number_str, clicked_button, inputs) == 2) {
                    set_and_menu(lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
                    Dialog dialog = new Dialog(activity, context, my_word, lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
                    if (clicked_button == "buttonlevels") {
                        dialog.dialog("Вы ошиблись, верное слово: " + my_word);
                    } else {
                        dialog.dialog("Вы ошиблись!!!");
                    }
                } else if (checker.check(number_str, clicked_button, inputs) == 3) {
                    number_str = checker.getNumber_str();
                    block(number_str);
                } else {
                    Dialog dialog = new Dialog(activity, context, my_word, lvl_in_main, lvl_in_easy, lvl_in_medium, lvl_in_hard);
                    dialog.dialog("Такого слова не существует!!!");
                }

            }
        });
    }


    public void set_and_menu(Integer lvl_in_main, Integer lvl_in_easy, Integer lvl_in_medium, Integer lvl_in_hard) {
        menu1.setLvl_in_main(lvl_in_main);
        menu1.setLvl_in_easy(lvl_in_easy);
        menu1.setLvl_in_medium(lvl_in_medium);
        menu1.setLvl_in_hard(lvl_in_hard);
        menu1.menu();

    }
}
