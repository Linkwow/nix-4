package chess.services;

public class AppLogic {

    public static Object[] startPosition(String input) {
        Object[] array = new Object[2];
        char letterPosition = input.charAt(0);
        int digitPosition = Integer.parseInt(String.valueOf(input.charAt(1)));
        if (Character.isLowerCase(letterPosition)) {
            letterPosition = Character.toUpperCase(letterPosition);
        }
        if (startPositionCheck(letterPosition, digitPosition)) {
            array[0] = letterPosition;
            array[1] = digitPosition;
        } else {
            throw new ArrayIndexOutOfBoundsException("Вы ввели недопустимые значения.");
        }
        return array;
    }

    private static boolean startPositionCheck(char ch, int i) {
        if ((ch >= 65 && ch <= 72) && (i >= 1 && i <= 8)) {
            return true;
        } else {
            return false;
        }
    }

    public static Object[] nextPosition(String input, Object[] currentPosition) {
        Object[] array = new Object[2];
        char letterPosition = input.charAt(0);
        int digitPosition = Integer.parseInt(String.valueOf(input.charAt(1)));
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
        if ((ch >= 65 && ch <= 72) && (i >= 1 && i <= 8)) {
            if (((letterPosition - 1 == ch) && (letterPosition - 1 >= 65)) && (digitPosition - 2 == i) && (digitPosition - 2 >= 1) ||
                    ((letterPosition + 1 == ch) && (letterPosition - 1 <= 72)) && (digitPosition + 2 ==i) && (digitPosition + 2 <= 8)){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean endCalculate(String s){
        if(Integer.parseInt(s) == 1 ){
            return false;
        } else {
            return true;
        }
    }
}