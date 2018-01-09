package old.enums;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ilya on 25.03.2017.
 */
enum FIO {
    NAME,
    MIDDLENAME,
    SURNAME
}

public class EnumEntry {

    public static void main(String[] args) {
        Map<FIO, String> table = new EnumMap<>(FIO.class);
        table.put(FIO.MIDDLENAME, "Al-ch");
        table.put(FIO.SURNAME, "VOLY");

        System.out.println(table);


        Map<FIO, String> table2 = new LinkedHashMap<>();
        table2.put(FIO.MIDDLENAME, "Al-ch");
        table2.put(FIO.NAME, "Ilya");
        table2.put(FIO.SURNAME, "VOLY");

        System.out.println(table2);

    }
}
