package NumbersInString.ServiceClasses;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class AddWithRegExp {

    final Scanner inputFromConsole = new Scanner(System.in);
    final Pattern patternRegExp = Pattern.compile("-?\\d+");
    private Matcher matcherObject;
    private int sum = 0;

    public String sendData() {
        return inputFromConsole.nextLine();
    }

    public void addNumbers(String s) {
        matcherObject = patternRegExp.matcher(s);
        while (matcherObject.find()) {
            sum += Integer.parseInt(matcherObject.group());
        }
        System.out.println("The sum of numbers in the row is : " + sum);
    }
}