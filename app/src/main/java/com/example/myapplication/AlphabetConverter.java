package com.example.myapplication;


import java.util.HashMap;
import java.util.Map;

public class AlphabetConverter {
    private static Map<Character, Integer> alphabetMap = new HashMap<>();


    public static String anti_cypher(Long number){
        String my_word = "";
        while (number > 0){
            Integer num_letter = Math.toIntExact(number % 100);
            number = number / 100;
            num_letter = (num_letter + 57) / 3;
            my_word += get_letter(num_letter);
        }
        return my_word;
    }


    public static Long cypher_word(String word){
        word = new StringBuilder(word).reverse().toString();
        long fullnumber = 0;
        createAlphabetMap();
        for (int i = 0; i < word.length(); i++){
            char letter = word.charAt(i);
            int number = getNumberForLetter(letter);
            fullnumber *= 100;
            fullnumber += (number * 3 - 57);
        }
        return fullnumber;
    }


    private static void createAlphabetMap() {
        char letter = 'а';
        int number = 20;

        for (int i = 0; i < 33; i++) {
            alphabetMap.put(letter, number);
            letter++;
            number++;
        }
    }

    private static int getNumberForLetter(char letter) {
        return alphabetMap.get(letter);
    }

    public static char get_letter(int num) {
        createAlphabetMap();
        for (Map.Entry<Character, Integer> entry : alphabetMap.entrySet()) {
            if (entry.getValue() == num) {
                return entry.getKey();
            }
        }
        return ' ';
}}
