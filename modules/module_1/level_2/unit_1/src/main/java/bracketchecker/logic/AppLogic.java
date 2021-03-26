package bracketchecker.logic;

public class AppLogic {

    private static int closeBracketCheck(int position, StringBuilder s, char ch) {
        int result = 0;
        char[] array = s.substring(position).toCharArray();
        for (int index = 0; index < array.length; index++) {
            if (array[index] == ch) {
                result = index;
                break;
            } else {
                result = -1;
            }
        }
        return result;
    }

    public static boolean check(String s) {
        StringBuilder sb = new StringBuilder(s);
        if(s.isEmpty()){
            return true;
        }
        int temp = 0;
        boolean b = false;
        for (int index = 0; index < s.length(); index++) {
            if (sb.charAt(index) == '(') {
                temp = closeBracketCheck(index, sb, ')');
                if (temp > 0) {
                    b = true;
                    sb.setCharAt(index, ' ');
                    sb.setCharAt(temp + index, ' ');
                } else {
                    b = false;
                    break;
                }
            } else if (sb.charAt(index) == '{') {
                temp = closeBracketCheck(index, sb, '}');
                if (temp > 0) {
                    b = true;
                    sb.setCharAt(index, ' ');
                    sb.setCharAt(temp + index, ' ');
                } else {
                    b = false;
                    break;
                }
            } else if (sb.charAt(index) == '[') {
                temp = closeBracketCheck(index, sb, ']');
                if (temp > 0) {
                    b = true;
                    sb.setCharAt(index, ' ');
                    sb.setCharAt(temp + index, ' ');
                } else {
                    b = false;
                    break;
                }
            } else if (sb.charAt(index) == ')' || sb.charAt(index) == ']' || sb.charAt(index) == '}'){
                b = false;
                break;
            }
        }
        return b;
    }
}