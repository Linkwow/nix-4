package LetterCounter.ServiceClasses;

import java.util.TreeMap;
import java.util.SortedMap;

public class CountWithMap {
    final static SortedMap<Character, Integer> valueStore = new TreeMap<Character, Integer>();

    public static void countLetters (String s) {
        for(int index = 0; index < s.length(); index++){
            Character tempChar = s.charAt(index);
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
