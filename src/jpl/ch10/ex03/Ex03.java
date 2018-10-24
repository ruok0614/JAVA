package jpl.ch10.ex03;

public class Ex03 {
    enum Week{
        MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY
    }
    public static boolean isWorkingDay(Week day) {
        switch (day) {
            case SUNDAY: case SATURDAY:
                return false;
            default:
                return true;
        }
    }

    public static void main(String[] args) {
        //boolean isWorking = WorkingDays.isWorkingDay(DaysOfTheWeek.SUNDAY);
        boolean isWorking = Ex03.isWorkingDay(Week.FRIDAY);
        System.out.println(isWorking);

    }
}
