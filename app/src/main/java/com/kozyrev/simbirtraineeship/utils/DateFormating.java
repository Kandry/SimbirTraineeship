package com.kozyrev.simbirtraineeship.utils;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.ChronoUnit;

public class DateFormating {

    public static String startEndDateFormat(long dateStart, long dateEnd, String pattern){
        String date = "";

        if (dateEnd > -1){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM");

            LocalDate startLocalDate = Instant.ofEpochMilli(dateStart).atZone(ZoneId.systemDefault()).toLocalDate();
            String startDate = formatter.format(startLocalDate);

            LocalDate endLocalDate = Instant.ofEpochMilli(dateEnd).atZone(ZoneId.systemDefault()).toLocalDate();
            String endDate = formatter.format(endLocalDate);

            LocalDate localDate = LocalDate.now();
            long diffDays = ChronoUnit.DAYS.between(localDate, startLocalDate);
            if (diffDays > 0) date = "Осталось " + diffDays + " дней ";

            date += "(" + startDate + " - " + endDate + ")";
        } else{
            date = dateFormat(dateStart, pattern);
        }

        return date;
    }

    public static String dateFormat(long date, String pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate localDate = Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDate();
        return formatter.format(localDate);
    }
}
