package com.ideas2it.dvdstore.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.model.DVD;

/**
 * Contains all the date related functions used while 
 * storing date related values.
 *
 * @version 1
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.common.Constants
 * @see com.ideas2it.dvdstore.model.DVD
 */
public class DateUtils {

    /**
     * Calculates the years, months and days between the present day and
     * and the given date.
     *
     * @param date
     *        Date object for which period difference days to be calculated.
     *
     * @return period
     *         String value of the calculated period difference.
     */
    public static String getPeriodDiff(Date date) {
        LocalDate localDate = date.toLocalDate();
        StringBuilder period = new StringBuilder();
        Period periodDiff = Period.between(localDate, LocalDate.now());
        if (0 < periodDiff.getYears()) {
            period.append(periodDiff.getYears())
                .append(Constants.LABEL_YEAR);
        }
        if (0 < periodDiff.getMonths()) {
            period.append(periodDiff.getMonths())
                .append(Constants.LABEL_MONTHS);
        }
        if (0 < periodDiff.getDays()) {
            period.append(periodDiff.getDays())
                .append(Constants.LABEL_DAYS);
        }
        period.append(Constants.LABEL_AGO);
        return period.toString();
    }

    /**
     * Calculates the days between current date and the passed date.
     *
     * @param date
     *        Date object for which days difference to be calculated.
     *
     * @return Long value of calculated days difference.
     */
    public static Long getDaysDiff(Date date) {
        return ChronoUnit.DAYS.between(date.toLocalDate(), LocalDate.now());
    }

    /**
     * Checks if the passed date is in future and returns true if it
     * is in future.
     * Finds the difference between the date passed and current date
     * if the difference is negative returns true.
     *
     * @param date
     *        LocalDate object of the date to be checked.
     *
     * @return returns true if the Date is in future.
     */
    public static Boolean inFuture(LocalDate date) {
        Period difference = Period.between(date, LocalDate.now());
        if ( 0 >= difference.getYears() 
                && 0 >= difference.getMonths()
                && 0 >= difference.getDays() ) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}










