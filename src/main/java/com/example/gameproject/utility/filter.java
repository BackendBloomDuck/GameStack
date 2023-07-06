package com.example.gameproject.utility;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class filter {

    public static List<String> stringToList(String s) {
        return Arrays.stream(s.split("\\|\\|")).map(String::strip).collect(Collectors.toList());
    }

    public static String listToString(List<String> list) {
        StringBuilder str = new StringBuilder();
        if (list.size() == 0)
            return str.toString();
        str.append(list.get(0));
        for (String s : list) {
            str.append(" || ").append(s);
        }
        return str.toString();
    }
}
