package guillermorosales.com.codechallenge.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Guillermo Romero on 1/14/16.
 */
public class UtilDate {

    public static String getLastMonthDateString() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return getCalendarString(calendar);
    }

    public static String getLastYearDateString() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,-1);
        return getCalendarString(calendar);
    }

    private static String getCalendarString(Calendar calendar){

        return "'"+new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())+"'";
    }

}
