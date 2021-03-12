package SchoolSchedule.DataClass;

public class Schedule {

    private static final int BREAK_MIN = 5;
    private static final int BREAK_MAX = 15;
    private static final int LESSONS_PER_DAY = 10;
    private static final int START_HOURS = 9;
    private static final int LESSONS_TIME = 45;

    private int
            breakTime,
            lessonsQuantity,
            endHours,
            endMinutes;
    private int[] breakArray = new int[LESSONS_PER_DAY];

    public Schedule(int quantity) {
        lessonsQuantity = quantity;
        breakArray[0] = breakTime;
        breakArray[1] = breakTime += BREAK_MIN;
        breakArray[2] = breakTime += BREAK_MAX;
        breakArray[3] = breakTime += BREAK_MIN;
        breakArray[4] = breakTime += BREAK_MAX;
        breakArray[5] = breakTime += BREAK_MIN;
        breakArray[6] = breakTime += BREAK_MAX;
        breakArray[7] = breakTime += BREAK_MIN;
        breakArray[8] = breakTime += BREAK_MAX;
        breakArray[9] = breakTime += BREAK_MIN;
        endHours = START_HOURS + (((lessonsQuantity * LESSONS_TIME) + breakArray[lessonsQuantity - 1]) / 60);
        endMinutes = (((lessonsQuantity * LESSONS_TIME) + breakArray[lessonsQuantity - 1]) % 60);
    }

    public int getHours(){
        return endHours;
    }

    public int getMinutes(){
        return endMinutes;
    }
}