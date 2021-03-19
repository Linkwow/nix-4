package methods;

public class ChangeString {

    private static String input;
    private static String pattern;
    private static StringBuilder sb = new StringBuilder();
    private static String s;

    private static void clear() {
        sb = new StringBuilder();
    }

    public static String reverse(String src) {
        for (int index = src.length() - 1; index >= 0; index--) {
            sb.append(src.charAt(index));
        }
        input = sb.toString();
        clear();
        return input;
    }

    public static String reverse(String src, String dest) {
        input = src;
        pattern = dest;
        for (int i = 0, k = 0; i < src.length(); i++) {
            if ((input.charAt(i) == pattern.charAt(k)) && ((i + pattern.length()) <= input.length())) {
                if (isPattern(i, k)) {
                    for (int x = 1; x <= pattern.length(); x++) {
                        sb.append(pattern.charAt(pattern.length() - x));
                        i++;
                    }
                    if(i < src.length())
                    sb.append(input.charAt(i));
                }
            } else {
                sb.append(input.charAt(i));
            }
        }
        return sb.toString();
    }

    private static boolean isPattern(int strInd, int strPat) {
        boolean patternTrue = false;
        for (int nextStrInd = strInd + 1, nextPatInd = strPat + 1; nextStrInd < strInd + pattern.length(); nextStrInd++, nextPatInd++) {
            if (input.charAt(nextStrInd) == pattern.charAt(nextPatInd)) {
                patternTrue = true;
            } else {
                patternTrue = false;
                sb.append(input.charAt(strInd));
                break;
            }
        }
        return patternTrue;
    }

    public static void main(String[] args) {
        System.out.println(ChangeString.reverse("Are you?"));
        System.out.println(ChangeString.reverse("No king, no kingdom", "king"));
    }
}

