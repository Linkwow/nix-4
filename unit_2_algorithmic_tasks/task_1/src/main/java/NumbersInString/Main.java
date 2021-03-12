package NumbersInString;

import NumbersInString.DataClass.DataClass;
import NumbersInString.ServiceClasses.AddWithRegExp;
import NumbersInString.ServiceClasses.AddWithChar;

public class Main {

    final static AddWithRegExp addWithRegExp = new AddWithRegExp();
    final static AddWithChar addWithChar = new AddWithChar();
    final static DataClass dataClass = new DataClass();

    public static void main(String[] args) {
        System.out.print("Please, enter your text : ");
        dataClass.set(addWithRegExp.sendData());
        System.out.println("Using method with RegExp");
        addWithRegExp.addNumbers(dataClass.get());
        System.out.println("Using method with Char");
        addWithChar.addNums(dataClass.get());
    }
}
