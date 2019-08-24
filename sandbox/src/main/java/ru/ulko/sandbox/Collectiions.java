package ru.ulko.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yulia on 24.08.2019.
 */
public class Collectiions {
    public static void main(String[] args){

        String[] langs = {"Java", "C#", "Python", "PHP"};

        List<String> languages1 = new ArrayList<>();
        languages1.add("Java");
        languages1.add("C#");
        languages1.add("Python");
        languages1.add("PHP");

        List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");

        for (String l: languages) {
            System.out.println("Я хочу научиться программировать на " + l);
        }
    }
}
