package NumbersInString.ServiceClasses;

public class AddWithChar {

    String StringTemp = "";
    int sum;

    public void addNums(String s) {
        for (int index = 0; index < s.length(); index++) {
            if (Character.isDigit(s.charAt(index))) {
                StringTemp += s.charAt(index);
            } else if (s.charAt(index) == '-') {
                if (index < s.length() - 1) {
                    if (Character.isDigit(s.charAt(index + 1))) {
                        if (!StringTemp.isEmpty()) {
                            sum += Integer.parseInt(StringTemp);
                        }
                        StringTemp = String.valueOf((s.charAt(index)));
                    } else {
                        if (!StringTemp.isEmpty()) {
                            sum += Integer.parseInt(StringTemp);
                        }
                        StringTemp = "";
                    }
                } else {
                    if (!StringTemp.isEmpty()) {
                        sum += Integer.parseInt(StringTemp);
                    }
                    StringTemp = "";
                }
            } else {
                if (!StringTemp.isEmpty()) {
                    sum += Integer.parseInt(StringTemp);
                }
                StringTemp = "";
            }
        }
        if (!StringTemp.isEmpty()) {
            sum += Integer.parseInt(StringTemp);
        }
        System.out.println("The sum of numbers in the row is : " + sum);
    }
}
