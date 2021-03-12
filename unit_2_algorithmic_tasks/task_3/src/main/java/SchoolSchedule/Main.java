package SchoolSchedule;

import SchoolSchedule.DataClass.Schedule;
import SchoolSchedule.ServiceClass.ServiceClass;

public class Main {
    public static void main(String[] args) {
        Schedule schoolSchedule = new Schedule(ServiceClass.sendLessonsQuantity());
        ServiceClass.printer(schoolSchedule.getHours(), schoolSchedule.getMinutes());
    }
}