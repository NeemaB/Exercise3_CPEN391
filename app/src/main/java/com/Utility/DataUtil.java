package com.Utility;

import com.codekrypt.greendao.db.Meal;

import java.util.Locale;

/**
 * Created by neema on 2017-03-08.
 */
public class DataUtil {


    private static String LOG_TAG = "DataUtil";

    public static int UPPER_CASE = 1;
    public static int LOWER_CASE = 0;

    /**
     * Static helper method that converts an int representation of a month
     * to a three character (ALL-CAPS) string representation of a month
     *
     * @param month
     * @return
     */
    public static String convertMonthToString(int month, int textCase) {
        switch (month) {

            case 1:
                return textCase == 1 ? "JAN" : "Jan";
            case 2:
                return textCase == 1 ? "FEB" : "Feb";
            case 3:
                return textCase == 1 ? "MAR" : "Mar";
            case 4:
                return textCase == 1 ? "APR" : "Apr";
            case 5:
                return textCase == 1 ? "MAY" : "May";
            case 6:
                return textCase == 1 ? "JUN" : "Jun";
            case 7:
                return textCase == 1 ? "JUL" : "Jul";
            case 8:
                return textCase == 1 ? "AUG" : "Aug";
            case 9:
                return textCase == 1 ? "SEP" : "Sep";
            case 10:
                return textCase == 1 ? "OCT" : "Oct";
            case 11:
                return textCase == 1 ? "NOV" : "Nov";
            case 12:
                return textCase == 1 ? "DEC" : "Dec";
        }
        return "";
    }

    /**
     * Static helper method for converting an int representation of a day to a
     * three character (ALL-CAPS) string representation
     */

    public static String convertDayToString(int day) {
        System.out.println(day);
        switch (day) {
            case 0:
                return "Sat";
            case 1:
                return "Sun";
            case 2:
                return "Mon";
            case 3:
                return "Tue";
            case 4:
                return "Wed";
            case 5:
                return "Thu";
            case 6:
                return "Fri";
        }
        return "";
    }

    public static String getTime(Meal meal) {

        boolean time_of_day = false;

        if (meal.getHour() >= 12)
            time_of_day = true;

        String s = String.format(Locale.US, "%02d:%02d %s",
                (meal.getHour() == 12 || meal.getHour() == 0) ? 12 : meal.getHour() % 12, meal.getMinute(),
                time_of_day ? "PM" : "AM");

        StringBuilder sb = new StringBuilder(s);

        if (s.charAt(0) == '0')
            sb.deleteCharAt(0);

        s = sb.toString();

        String date = DataUtil.convertMonthToString(meal.getMonth(), DataUtil.LOWER_CASE) + " " + meal.getDay();

        date = date + "," + " " + s;

        return date;
    }


}
