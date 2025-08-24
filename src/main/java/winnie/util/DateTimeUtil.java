package winnie.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import winnie.exception.InvalidDateTimeException;

public class DateTimeUtil {
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter INPUT_DATE_ONLY_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
    private static final DateTimeFormatter OUTPUT_DATE_ONLY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter STORAGE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static LocalDateTime parseDateTime(String dateTimeString) throws InvalidDateTimeException {
        dateTimeString = dateTimeString.trim();
        
        try {
            return LocalDateTime.parse(dateTimeString, INPUT_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            try {
                return LocalDateTime.parse(dateTimeString + " 0000", INPUT_DATE_FORMAT);
            } catch (DateTimeParseException e2) {
                throw new InvalidDateTimeException(dateTimeString);
            }
        }
    }

    public static String formatForDisplay(LocalDateTime dateTime) {
        if (dateTime.getHour() == 0 && dateTime.getMinute() == 0) {
            return dateTime.format(OUTPUT_DATE_ONLY_FORMAT);
        }
        return dateTime.format(OUTPUT_FORMAT);
    }

    public static String formatForStorage(LocalDateTime dateTime) {
        return dateTime.format(STORAGE_FORMAT);
    }

    public static LocalDateTime parseFromStorage(String storedDateTime) throws DateTimeParseException {
        return LocalDateTime.parse(storedDateTime, STORAGE_FORMAT);
    }
}