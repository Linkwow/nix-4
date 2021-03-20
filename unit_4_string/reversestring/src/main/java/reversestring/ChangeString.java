package reversestring;

public class ChangeString {

    public static String reverse(String src) {
        String[] arrayOfWords = src.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String arrayElement : arrayOfWords) {
            for (int index = arrayElement.length() - 1; index >= 0; index--) {
                sb.append(arrayElement.charAt(index));
            }
            sb.append(' ');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static String reverse(String src, String dest) {
        StringBuilder sb = new StringBuilder();
        for (int index = 0, compareIndex = 0; index < src.length(); index++) {
            if ((src.charAt(index) == dest.charAt(compareIndex)) && ((index + dest.length()) <= src.length())) {
                if (isPattern(index, compareIndex, src, dest, sb)) {
                    for (int patternIndex = 1; patternIndex <= dest.length(); patternIndex++) {
                        sb.append(dest.charAt(dest.length() - patternIndex));
                        index++;
                    }
                    if (index < src.length()) {
                        sb.append(src.charAt(index));
                    }
                }
            } else {
                sb.append(src.charAt(index));
            }
        }
        return sb.toString();
    }

    private static boolean isPattern(int strInd, int strPat, String input, String pattern, StringBuilder sb) {
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

    public static String reverse(String src, int firstIndex, int lastIndex) {
        StringBuilder sb = new StringBuilder();
        StringBuilder result = new StringBuilder();
        Character[] sourceArray = new Character[src.length()];
        String s, temp;
        for (int index = 0; index < src.length(); index++) {
            sourceArray[index] = src.charAt(index);
        }
        sb.append(src);
        s = sb.substring(firstIndex, lastIndex + 1);
        temp = ChangeString.reverse(s);
        for (int index = firstIndex, tempIndex = 0; tempIndex < temp.length(); index++, tempIndex++){
            sourceArray[index] = temp.charAt(tempIndex);
        }
        for (int index = 0; index < src.length(); index++){
            result.append(sourceArray[index]);
        }
        return result.toString();
    }
}