package enums;

import javax.management.OperationsException;
import java.util.EnumMap;
import java.util.EnumSet;

public class EnumSetAndEnumMapExample {
    public static void main(String[] args) {
        EnumSet<Permission> set = EnumSet.of(Permission.READ, Permission.WRITE);
        EnumMap<Permission, String> map = new EnumMap<>(Permission.class);

        map.put(Permission.READ, "Allowed");
        map.put(Permission.EXECUTE, "Denied");

        System.out.println("Permissions: " + set);
        System.out.println("Map: " + map);
    }
}

enum Permission {
    READ,
    WRITE,
    EXECUTE
}
