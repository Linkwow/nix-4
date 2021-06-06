package ua.nix.academy.utitlity.parsers;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeParser {

    public static ZonedDateTime timeDateGetFromString(String dateTime) {
        StringBuilder stringBuilder = new StringBuilder(dateTime.replace(' ', 'T'));
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        stringBuilder.append(zonedDateTime.getOffset());
        stringBuilder.append("[");
        stringBuilder.append(zonedDateTime.getZone());
        stringBuilder.append("]");
        zonedDateTime = ZonedDateTime.parse(stringBuilder.toString(), DateTimeFormatter.ISO_ZONED_DATE_TIME);
        return zonedDateTime;
    }
}
