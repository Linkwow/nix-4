package LetterCounter;

import LetterCounter.ServiceClasses.CountWithMap;
import NumbersInString.DataClass.DataClass;
import NumbersInString.ServiceClasses.AddWithRegExp;

public class Main {
    static final DataClass dataClass = new DataClass();
    static final AddWithRegExp serviceObject = new AddWithRegExp();
    static final CountWithMap countWithMap = new CountWithMap();

    public static void main(String[] args){
        System.out.print("Please, enter your text : ");
        dataClass.set(serviceObject.sendData());
        countWithMap.countLetters(dataClass.get());
    }
}
