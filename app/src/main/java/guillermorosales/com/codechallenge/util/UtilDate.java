package guillermorosales.com.codechallenge.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by yasminegutierrez on 1/14/16.
 */
public class UtilDate {


    public static String getCurrentDayString(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        int month;
        int year;
        int day;

        month =calendar.get(Calendar.MONTH)+1;
        year = calendar.get(Calendar.YEAR);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        return "'"+year+"-"+month+"-"+day+"'";
    }


    public static String getLastMonthDateString(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        int month;
        int year;
        int day;

        if(calendar.get(Calendar.MONTH) == 0){
            month = 12;
            year = calendar.get(Calendar.YEAR)-1;
        }else{
            month =calendar.get(Calendar.MONTH)+1;
            year = calendar.get(Calendar.YEAR);
        }

        day = calendar.get(Calendar.DAY_OF_MONTH);

        return "'"+year+"-"+month+"-"+day+"'";
    }


    public static String getLastYearDateString(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        int month;
        int year;
        int day;

        day = calendar.get(Calendar.DAY_OF_MONTH);
        month =calendar.get(Calendar.MONTH)+1;
        year = calendar.get(Calendar.YEAR)-1;

        return "'"+year+"-"+month+"-"+day+"'";
    }



}
