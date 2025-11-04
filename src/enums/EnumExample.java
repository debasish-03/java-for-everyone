package enums;

/**
 * An enum (short for enumeration) is a special data type that represents a fixed set of constants.
 * Enums are type-safe, meaning you can’t assign any value other than the defined constants.
 * **/
public class EnumExample {
    public static void main(String[] args) {
        // Enum methods
        Day[] days = Day.values();
        for (Day d: days) {
            System.out.println("Ordinal: " + d.ordinal() + ", name: " + d.name());
        }

        Day today = Day.TUESDAY;

        switch (today) {
            case MONDAY:
                System.out.println("Start of week");
                break;
            case FRIDAY:
                System.out.println("Weekend loading");
                break;
            default:
                System.out.println("Midweek day");
        }
    }
}

enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
