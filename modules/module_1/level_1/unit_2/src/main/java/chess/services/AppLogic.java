package chess.services;

public class AppLogic {

    public static Object[] startPosition(String input) {
        Object[] array = new Object[2];
        char letterPosition = input.charAt(0);
        int digitPosition = Integer.parseInt(input,1,input.length(), 10);
        if (Character.isLowerCase(letterPosition)) {
            letterPosition = Character.toUpperCase(letterPosition);
        }
        array[0] = letterPosition;
        array[1] = digitPosition;
        return array;
    }

    public static Object[] nextPosition(String input, Object[] currentPosition) {
        Object[] array = new Object[2];
        char letterPosition = input.charAt(0);
        int digitPosition = Integer.parseInt(input,1,input.length(), 10);
        if (Character.isLowerCase(letterPosition)) {
            letterPosition = Character.toUpperCase(letterPosition);
        }
        if (nextPositionCheck(letterPosition, digitPosition, currentPosition)) {
            array[0] = letterPosition;
            array[1] = digitPosition;
        } else {
            throw new ArrayIndexOutOfBoundsException("Вы ввели недопустимые значения.");
        }
        return array;
    }

    private static boolean nextPositionCheck(char ch, int i, Object[] currentPosition) {
        char letterPosition = (char) currentPosition[0];
        int digitPosition = (int) currentPosition[1];
        if(digitPosition + i < 0){
            digitPosition = 0;
        }
        if ((ch == letterPosition + 1) || (ch == letterPosition - 1)) {
            if (i == digitPosition + 2 || i == digitPosition - 2) {
                return true;
            } else {
                return false;
            }
        } else if (ch == letterPosition + 2 || ch == letterPosition - 2) {
            if (i == digitPosition + 1 || i == digitPosition - 1) {
                return true;
            } else {
                return false;
            }
        } else if (ch < letterPosition + 1 && ch == 'A') {
            if (i == digitPosition + 2 || i == digitPosition - 2) {
                return true;
            } else {
                return false;
            }
        } else if (ch < letterPosition + 1 && ch == 'B') {
            if (i == digitPosition + 1 || i == digitPosition - 1) {
                return true;
            } else {
                return false;
            }
        } else if (ch > letterPosition + 1 && ch == 'Z') {
            if (i == digitPosition + 2 || i == digitPosition - 2) {
                return true;
            } else if (i == digitPosition + 1 || i == digitPosition - 1) {
                return true;
            } else {
                return false;
            }
        } else if (ch > letterPosition + 1 && ch == 'Y') {
            if (i == digitPosition + 1 || i == digitPosition - 1) {
                return true;
            } else if (i == digitPosition + 2 || i == digitPosition - 2){
                return true;
            } else{
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean endCalculate(String s) {
        if (Integer.parseInt(s) == 1) {
            return false;
        } else {
            return true;
        }
    }
}