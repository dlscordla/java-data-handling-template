package com.epam.izh.rd.online.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;

public class SimpleDateService implements DateService {


    @Override
    public String parseDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }


    @Override
    public LocalDateTime parseString(String string) {
        return LocalDateTime.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }


    @Override
    public String convertToCustomFormat(LocalDate localDate, DateTimeFormatter formatter) {
        return localDate.format(formatter);
    }


    @Override
    public long getNextLeapYear() {
        int year = Year.now().getValue();
        while (!Year.of(year).isLeap()) {
            year++;
        }
        return year;
    }


    @Override
    public long getSecondsInYear(int year) {
        long secondsInADay = 24 * 60 * 60;
        return Year.of(year).length() * secondsInADay;
    }
}
