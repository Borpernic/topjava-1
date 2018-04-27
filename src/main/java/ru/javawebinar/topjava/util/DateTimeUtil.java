package ru.javawebinar.topjava.util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static final LocalDate MIN_DATE = LocalDate.of(1, 1, 1);
    public static final LocalDate MAX_DATE = LocalDate.of(3000, 1, 1);

    public static <T extends Comparable<? super T>> boolean isBetween(T value, T start, T end) {
        return value.compareTo(start) >= 0 && value.compareTo(end) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate parseLocalDate(String localDateStr) {
        return StringUtils.isEmpty(localDateStr) ? null : LocalDate.parse(localDateStr);
    }

    public static LocalTime parseLocalTime(String localTimeStr) {
        return StringUtils.isEmpty(localTimeStr) ? null : LocalTime.parse(localTimeStr);
    }

    public static LocalDateTime parseLocalDateTimeForStartFilter(String localDateStr, String localTimeStr) {

        return LocalDateTime.of(parseLocalDate(localDateStr) != null ? parseLocalDate(localDateStr) : DateTimeUtil.MIN_DATE,
                parseLocalTime(localTimeStr) != null ? parseLocalTime(localTimeStr) : LocalTime.MIN);
    }

    public static LocalDateTime parseLocalDateTimeForEndFilter(String localDateStr, String localTimeStr) {

        return LocalDateTime.of(parseLocalDate(localDateStr) != null ? parseLocalDate(localDateStr) : DateTimeUtil.MAX_DATE,
                parseLocalTime(localTimeStr) != null ? parseLocalTime(localTimeStr) : LocalTime.MAX);
    }
}
