package com.school.comp2522.assignments.a2;

public class Utils {
    public static String capitalize(final String string) {
        String lowerCased = string.toLowerCase();
        return Character.toUpperCase(lowerCased.charAt(0)) + lowerCased.substring(1);
    }
}
