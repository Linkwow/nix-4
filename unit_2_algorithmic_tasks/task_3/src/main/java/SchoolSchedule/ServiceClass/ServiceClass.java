package SchoolSchedule.ServiceClass;

import java.util.Scanner;

public class ServiceClass {

    private ServiceClass(){}

    public static int sendLessonsQuantity() {
        Scanner userInput = new Scanner(System.in);
        return userInput.nextInt();
    }

    public static void printer(int hours, int minutes){
        System.out.println("Lessons ends at : " + hours + " hours and " + minutes + " minutes");
    }
}