package uniquenumbers.service;

public class AppLogic {

    public static int uniqueNumbers(Number[] arrayOfNumbers) {
        boolean unique = false;
        int count = 0;
        for (int outerIndex = 0; outerIndex < arrayOfNumbers.length; outerIndex++) {
            for (int innerIndex = 0; innerIndex < arrayOfNumbers.length; innerIndex++) {
                if (outerIndex != innerIndex) {
                    if (arrayOfNumbers[outerIndex].equals(arrayOfNumbers[innerIndex])) {
                        unique = false;
                        break;
                    } else {
                        unique = true;
                    }
                }
            }
            if (unique) {
                count++;
            }
        }
        return count;
    }

    public static Number[] parser(String[] inputArray){
        Number [] arrayOfNumber = new Number[inputArray.length];
        for (int index = 0; index < inputArray.length; index++){
            arrayOfNumber[index] = Double.parseDouble(inputArray[index]);
        }
        return arrayOfNumber;
    }
}
