package ua.nix.datestandart.entity;

import java.util.Random;

public class RandomDate {
    private final Random random = new Random();
    private final int year;
    private final String month;
    private final String day;
    private final String info;

    public RandomDate() {
        year = random.nextInt(1022) + 1000;
        month = randomMonth();
        day = randomDay(Integer.parseInt(month));
        info = createInfo();
    }

    private String randomMonth(){
        String month;
        int value = random.nextInt(12) + 1;
        if(value < 10){
            month = "0" + value;
        } else {
            month = String.valueOf(value);
        }
        return month;
    }

    private String randomDay(int month) {
        String day;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            day = String.valueOf(random.nextInt(30) + 1);
        } else if (month == 2) {
            day = String.valueOf(random.nextInt(28) + 1);
        } else {
            day = String.valueOf(random.nextInt(30) + 1);
        }
        if (day.length() == 1) {
            return "0" + day;
        } else {
            return day;
        }
    }

    private String createInfo() {
        String date = "";
        switch (random.nextInt(4)) {
            case 0:
                date = year + "/" + month + "/" + day;
                break;
            case 1:
                date = day + "/" + month + "/" + year;
                break;
            case 2:
                date = month + "-" + day + "-" + year;
                break;
            case 3:
                date = day + "." + month + "." + year;
                break;
            default:
                break;
        }
        return date;
    }


    public String getInfo() {
        return info;
    }
}