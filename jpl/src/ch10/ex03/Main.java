package ch10.ex03;

public class Main {

    public static void main(String[] args) {
        System.out.println("That Monday is workDay is " + isWorkDayWithIfElse(DayOfTheWeek.MONDAY));
        System.out.println("That Saturday is workDay is " + isWorkDayWithIfElse(DayOfTheWeek.SATURDAY));
        System.out.println("That Wednesday is workDay is " + isWorkDayWithSwitchCase(DayOfTheWeek.WEDNESDAY));
        System.out.println("That Sunday is workDay is " + isWorkDayWithSwitchCase(DayOfTheWeek.SUNDAY));
    }

    public static boolean isWorkDayWithIfElse(DayOfTheWeek dayOfTheWeek) {
        if (dayOfTheWeek == DayOfTheWeek.MONDAY) {
            return true;
        } else if (dayOfTheWeek == DayOfTheWeek.TUESDAY) {
            return true;
        } else if (dayOfTheWeek == DayOfTheWeek.WEDNESDAY) {
            return true;
        } else if (dayOfTheWeek == DayOfTheWeek.THURSDAY) {
            return true;
        } else if (dayOfTheWeek == DayOfTheWeek.FRIDAY) {
            return true;
        } else if (dayOfTheWeek == DayOfTheWeek.SATURDAY) {
            return false;
        } else if (dayOfTheWeek == DayOfTheWeek.SUNDAY) {
            return false;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static boolean isWorkDayWithSwitchCase(DayOfTheWeek dayOfTheWeek) {
        switch (dayOfTheWeek) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                return true;
            case SATURDAY:
            case SUNDAY:
                return false;
            default:
                throw new IllegalArgumentException();
        }
    }

}
