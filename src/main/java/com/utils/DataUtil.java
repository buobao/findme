package com.utils;

/**
 * Created by dqf on 2015/7/15.
 */

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataUtil extends DateUtils {
    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static String DateToString(Date date, String pattern){
        String strDateTime = null;
        SimpleDateFormat formater = new SimpleDateFormat(pattern);
        strDateTime = date == null ? null : formater.format(date);
        return strDateTime;

    }

    public static String DateToString(Date date) {
        String _pattern = "yyyy-MM-dd";
        return date == null ? null : DateToString(date, _pattern);
    }

    public static String DateTimeToString(Date date) {
        String _pattern = "yyyy-MM-dd HH:mm:ss";
        return date == null ? null : DateToString(date, _pattern);
    }

    public static String DateMinToString(Date date) {
        String _pattern = "yyyy-MM-dd HH:mm";
        return date == null ? null : DateToString(date, _pattern);
    }

    public static Date StringToDate(String str, String pattern) {
        Date dateTime = null;
        try {
            if (str != null && !str.equals("")) {
                SimpleDateFormat formater = new SimpleDateFormat(pattern);
                dateTime = formater.parse(str);
            }
        } catch (Exception ex) {
        }
        return dateTime;
    }

    public static Date StringToDate(String str) {
        String _pattern = "yyyy-MM-dd";
        return StringToDate(str, _pattern);
    }

    public static Date StringToDateTime(String str) {
        String _pattern = "yyyy-MM-dd HH:mm:ss";
        return StringToDate(str, _pattern);
    }

    public static Timestamp StringToDateHMS(String str) throws Exception {
        Timestamp time = null;
        time = Timestamp.valueOf(str);
        return time;
    }

    public static Date YmdToDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    public static String communityDateToString(Date date) {
        SimpleDateFormat formater = new SimpleDateFormat("MM/dd HH:mm:ss");
        String strDateTime = date == null ? null : formater.format(date);
        return strDateTime;
    }

    public static Date getMaxDateOfDay(Date date) {
        if (date == null) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(11, calendar.getActualMaximum(11));
            calendar.set(12, calendar.getActualMaximum(12));
            calendar.set(13, calendar.getActualMaximum(13));
            calendar.set(14, calendar.getActualMaximum(14));
            return calendar.getTime();
        }
    }
    public static Date getMaxDateOfMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }
    public static Date getMinDateOfMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,0);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        return calendar.getTime();
    }
    public static Date getMinDateOfDay(Date date) {
        if (date == null) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(11, calendar.getActualMinimum(11));
            calendar.set(12, calendar.getActualMinimum(12));
            calendar.set(13, calendar.getActualMinimum(13));
            calendar.set(14, calendar.getActualMinimum(14));
            return calendar.getTime();
        }
    }

    public static Date getAfterYears(Date date, int afterYears) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, afterYears);
        return cal.getTime();
    }

    public static Date getAfterMonths(Date date, int afterMonths) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, afterMonths);
        return cal.getTime();
    }

    public static Date getAfterDay(Date date, int afterDays) {
        if(date!=null && !date.equals("")){
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, afterDays);
            return cal.getTime();
        }else{
            return null;
        }
    }

    public static Date getAfterMinutes(Date date, int afterMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, afterMinutes);
        return cal.getTime();
    }

    public static int DateDiff(Date date1, Date date2) {
        int i = (int) ((date1.getTime() - date2.getTime()) / 3600 / 24 / 1000);
        return i;
    }

    public static int YearDiff(Date date1, Date date2) {
        int i = (int) ((date1.getTime() - date2.getTime()) / 3600 / 24 / 1000 /365);
        return i;
    }

    public static int MonthDiff(Date date1, Date date2) {
        int i = (int) ((date1.getTime() - date2.getTime()) / 3600 / 24 / 1000 /12);
        return i;
    }

    public static long MinDiff(Date date1, Date date2) {
        long i = (long) ((date1.getTime() - date2.getTime()) / 1000 / 60);
        return i;
    }

    public static long TimeDiff(Date date1, Date date2) {
        long i = (long) ((date1.getTime() - date2.getTime()));
        return i;
    }


    public static String friendly_time(Date time) {
        if(time == null) {
            return "";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        //判断是否是同一天
        String curDate = dateFormater2.get().format(cal.getTime());
        String paramDate = dateFormater2.get().format(time);
        if(curDate.equals(paramDate)){
            int hour = (int)((cal.getTimeInMillis() - time.getTime())/3600000);
            if(hour == 0)
                ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1)+"分钟前";
            else
                ftime = hour+"小时前";
            return ftime;
        }

        long lt = time.getTime()/86400000;
        long ct = cal.getTimeInMillis()/86400000;
        int days = (int)(ct - lt);
        if(days == 0){
            int hour = (int)((cal.getTimeInMillis() - time.getTime())/3600000);
            if(hour == 0)
                ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1)+"分钟前";
            else
                ftime = hour+"小时前";
        }
        else if(days == 1){
            ftime = "昨天";
        }
        else if(days == 2){
            ftime = "前天";
        }
        else if(days > 2 && days <= 10){
            ftime = days+"天前";
        }
        else if(days > 10){
            ftime = dateFormater2.get().format(time);
        }
        return ftime;
    }

    public static Date TurnsinaDate(String str){
        if (str != null && StringUtils.isNotEmpty(str)){
            Pattern pattern = Pattern.compile("^\\d{4}(\\-|年|\\.)\\d{1,2}月\\d{1,2}日\\d{1,2}:\\d{1,2}");
            Matcher matcher = pattern.matcher(str);
            boolean b= matcher.matches();
            if (b){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                str = str.replace("日"," ");
                str = str.replace("年","-");
                str = str.replace("月","-");
                try{
                    Date date = sdf.parse(str);

                    Calendar currentDate = new GregorianCalendar();
                    currentDate.set(Calendar.HOUR_OF_DAY,0);
                    currentDate.set(Calendar.MINUTE,0);
                    currentDate.set(Calendar.SECOND,0);
                    Date today = (Date)currentDate.getTime().clone();
                    if (date.getTime() > today.getTime()) {
                        return date;
                    }
                }catch (ParseException e){
                    System.out.println("ParseException");
                }
            }
        }
        return null;
    }
}



























