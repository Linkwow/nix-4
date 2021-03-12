package LetterCounter.ServiceClasses;

import NumbersInString.ServiceClasses.AddWithRegExp;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.SortedMap;
import java.util.Scanner;

public class CountWithMap {
    final static SortedMap<Character, Integer> valueStore = new TreeMap<Character, Integer>();

    public static void countLetters (String s) {
        for(int index = 0; index < s.length(); index++){
            Character tempChar = (Character) s.charAt(index);
            if(Character.isLetter(tempChar)){
                if (valueStore.containsKey(tempChar)) {
                    valueStore.put(tempChar, (valueStore.get(tempChar)) + 1);
                } else {
                    valueStore.put(tempChar, 1);
                }
            }
        }
        for(Character c : valueStore.keySet()){
            System.out.println("Letter " + c + " have count " + valueStore.get(c));
        }
    }
}
