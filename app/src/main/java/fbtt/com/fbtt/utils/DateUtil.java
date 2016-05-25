package fbtt.com.fbtt.utils;

/**
 * Created by Administrator on 2016/4/13.
 */

import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Pattern;


public class DateUtil {
    public static final String TAG = "DateUtil";
    /**
     * new a Calendar instance
     */
    static GregorianCalendar cldr = new GregorianCalendar();

    /**
     * the milli second of a day
     */
    public static final long DAYMILLI = 24 * 60 * 60 * 1000;

    /**
     * the milli seconds of an hour
     */
    public static final long HOURMILLI = 60 * 60 * 1000;

    /**
     * the milli seconds of a minute
     */
    public static final long MINUTEMILLI = 60 * 1000;

    /**
     * the milli seconds of a second
     */
    public static final long SECONDMILLI = 1000;

    /**
     * added time
     */
    public static final String TIMETO = " 23:59:59";

    /**
     * set the default time zone
     */
    static {
        cldr.setTimeZone(java.util.TimeZone.getTimeZone("GMT+9:00"));
    }

    /**
     * flag before
     */
    public static final transient int BEFORE = 1;

    /**
     * flag after
     */
    public static final transient int AFTER = 2;

    /**
     * flag equal
     */
    public static final transient int EQUAL = 3;

    /**
     * date format dd/MMM/yyyy:HH:mm:ss +0900
     */
    public static final String TIME_PATTERN_LONG = "dd/MMM/yyyy:HH:mm:ss +0900";

    /**
     * date format dd/MM/yyyy:HH:mm:ss +0900
     */
    public static final String TIME_PATTERN_LONG2 = "dd/MM/yyyy:HH:mm:ss +0900";

    /**
     * date format yyyy-MM-dd HH:mm:ss
     */
    public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * date format yyyy-MM-dd HH:mm:ss SSS
     */
    public static final String TIME_FULL_PATTERN = "yyyy-MM-dd HH:mm:ss SSS";

    public static final String TIME_PATTERN_1 = "yyyy-MM-dd HH:mm";

    /**
     * date format YYYY-MM-DD HH24:MI:SS
     */
    public static final String DB_TIME_PATTERN = "YYYY-MM-dd HH:mm:ss";

    /**
     * date format dd/MM/yy HH:mm:ss
     */
    public static final String TIME_PATTERN_SHORT = "dd/MM/yy HH:mm:ss";

    /**
     * date format dd/MM/yy HH24:mm
     */
    public static final String TIME_PATTERN_SHORT_1 = "yyyy/MM/dd HH:mm";

    /**
     * date format yyyy年MM月dd日 HH:mm:ss
     */
    public static final String TIME_PATTERN_SHORT_2 = "yyyy年MM月dd日 HH:mm:ss";
    public static final String ISO8601_DATETIME_PATTERN = "yyyy-MM-dd\'T\'HH:mm:ss";
    /**
     * date format yyyyMMddHHmmss
     */
    public static final String TIME_PATTERN_SESSION = "yyyyMMddHHmmss";

    /**
     * date format yyyyMMdd
     */
    public static final String DATE_FMT_0 = "yyyyMMdd";

    /**
     * date format yyyy/MM/dd
     */
    public static final String DATE_FMT_1 = "yyyy/MM/dd";

    /**
     * date format yyyy/MM/dd hh:mm:ss
     */
    public static final String DATE_FMT_2 = "yyyy/MM/dd HH:mm:ss";

    /**
     * date format yyyy-MM-dd
     */
    public static final String DATE_FMT_3 = "yyyy-MM-dd";

    /**
     * date format yyyy年MM月dd日
     */
    public static final String DATE_FMT_4 = "yyyy年MM月dd日";

    /**
     * date format yyyy-MM-dd HH
     */
    public static final String DATE_FMT_5 = "yyyy-MM-dd HH";

    /**
     * date format yyyy-MM
     */
    public static final String DATE_FMT_6 = "yyyy-MM";

    /**
     * date format yyyyMM
     */
    public static final String DATE_FMT_7 = "yyyyMM";

    /**
     * date format HHmm
     */
    public static final String DATE_FMT_8 = "HHmm";

    /**
     * date format yyyy/MM/dd hh:mm:ss
     */
    public static final String DATE_FMT_9 = "yyyy.MM.dd hh:mm:ss";

    /**
     * 账单初始时间1900-01-01 00:00:00
     */
    public static final Date BILL_INIT_DATE = DateUtil.getDateFromString("1900-01-01 00:00:00");

    /**
     * 字串转为日期
     *
     * @param dateStr
     * @return
     */
    public static Date getDateFromString(String dateStr) {
        return getDateFromString(dateStr, null);
    }

    public static Date getDay(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FMT_3);
        String str = dateFormat.format(date);
        return getDateFromString(str, null);
    }

    /**
     * 字串转为日期
     *
     * @param dateStr
     * @return
     */
    public static Date getDateTimeFromString(String dateStr) {
        return getDateFromString(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 字串转为日期
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date getDateFromString(String dateStr, String pattern) {
        if ((pattern == null) || ("".equals(pattern))) {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return date;
    }

    /**
     * 字串转为日期
     *
     * @param dateStr
     * @param pattern
     * @param locale
     * @return
     */
    public static Date getDateFromString(String dateStr, String pattern, Locale locale) {
        if ((pattern == null) || ("".equals(pattern))) {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern, locale);
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return date;
    }

    /**
     * 日期转为字串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getStrFromDate(Date date, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        String s = df.format(date);
        return s;
    }

    /**
     * 日期转字串
     *
     * @param date
     * @return
     */
    public static String getLongStrFromDate(Date date) {
        return getStrFromDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 日期转字串
     *
     * @param date
     * @return
     */
    public static String getDateStrFromDate(Date date) {
        return getStrFromDate(date, "yyyy-MM-dd");
    }

    /**
     * 时间格式转换
     *
     * @param timeValue
     * @return
     */
    public static Date format(String timeValue) {
        if (StringUtils.isEmpty(timeValue)) {
            return null;
        }
        timeValue = timeValue.replaceAll("[年月/\\\\]", "-");
        timeValue = timeValue.replaceAll("<hr>|日|\\s+", "");
        if (Pattern.matches("^[\\d/-]{1,10}$", timeValue)) {
            int length = timeValue.length();
            switch (length) {
                case 10:
                    break;
                case 8:
                    timeValue = String.format("%s-%s-%s", timeValue.substring(0, 4), timeValue.substring(4, 6),
                            timeValue.substring(6, 8));
                    break;
                case 1:
                    timeValue = "1900-01-0" + timeValue;
                    break;
                case 2:
                    timeValue = "1900-01-" + timeValue;
                    break;
                case 3:
                    timeValue = "1900-0" + timeValue.substring(0, 1) + "-" + timeValue.substring(1, 3);
                    break;
                case 4:
                    timeValue = "1900-" + timeValue.substring(0, 2) + "-" + timeValue.substring(2, 4);
                    break;
                case 5:
                    timeValue = "1900-" + timeValue.replace("/", "-");
                    break;
                case 6:
                    timeValue = String.format("19%s-%s-%s", timeValue.substring(0, 2), timeValue.substring(2, 4),
                            timeValue.substring(4, 6));
                    break;
            }
            return getDateFromString(timeValue);
        }
        return null;
    }

    /**
     * change string to date 将String类型的日期转成Date类型
     *
     * @param sDate the date string
     * @param sFmt  the date format
     * @return Date object
     */
    public static Date toDate(String sDate, String sFmt) {
        if (StringUtils.isEmpty(sDate) || StringUtils.isEmpty(sFmt)) {
            return null;
        }

        SimpleDateFormat sdfFrom = null;
        Date dt = null;
        try {
            sdfFrom = new SimpleDateFormat(sFmt);
            dt = sdfFrom.parse(sDate);
        } catch (Exception ex) {
            LogUtils.e(TAG, ex.toString());
            return null;
        } finally {
            sdfFrom = null;
        }

        return dt;
    }

    /**
     * change date to string 将日期类型的参数转成String类型
     *
     * @param dt a date
     * @return the format string
     */
    public static String toString(Date dt) {
        return toString(dt, DATE_FMT_0);
    }

    /**
     * change date object to string 将String类型的日期转成Date类型
     *
     * @param dt   date object
     * @param sFmt the date format
     * @return the formatted string
     */
    public static String toString(Date dt, String sFmt) {
        if (null == dt || StringUtils.isEmpty(sFmt)) {
            return null;
        }

        SimpleDateFormat sdfFrom = null;
        String sRet = null;
        try {
            sdfFrom = new SimpleDateFormat(sFmt);
            sRet = sdfFrom.format(dt).toString();
        } catch (Exception ex) {
            LogUtils.e(TAG, ex.toString());
            return null;
        } finally {
            sdfFrom = null;
        }

        return sRet;
    }

    /**
     * 获取Date所属月的最后一天日期
     *
     * @param date
     * @return Date 默认null
     */
    public static Date getMonthLastDate(Date date) {
        if (null == date) {
            return null;
        }

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.HOUR_OF_DAY, 23);
        ca.set(Calendar.MINUTE, 59);
        ca.set(Calendar.SECOND, 59);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        ca.add(Calendar.MONTH, 1);
        ca.add(Calendar.DAY_OF_MONTH, -1);

        Date lastDate = ca.getTime();
        return lastDate;
    }

    /**
     * 获取Date所属月的最后一天日期
     *
     * @param date
     * @param pattern
     * @return String 默认null
     */
    public static String getMonthLastDate(Date date, String pattern) {
        Date lastDate = getMonthLastDate(date);
        if (null == lastDate) {
            return null;
        }

        if (StringUtils.isEmpty(pattern)) {
            pattern = TIME_PATTERN;
        }

        return toString(lastDate, pattern);
    }

    /**
     * 获取Date所属月的第一天日期
     *
     * @param date
     * @return Date 默认null
     */
    public static Date getMonthFirstDate(Date date) {
        if (null == date) {
            return null;
        }

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.DAY_OF_MONTH, 1);

        Date firstDate = ca.getTime();
        return firstDate;
    }

    /**
     * 获取Date所属月的第一天日期
     *
     * @param date
     * @param pattern
     * @return String 默认null
     */
    public static String getMonthFirstDate(Date date, String pattern) {
        Date firstDate = getMonthFirstDate(date);
        if (null == firstDate) {
            return null;
        }

        if (StringUtils.isEmpty(pattern)) {
            pattern = TIME_PATTERN;
        }

        return toString(firstDate, pattern);
    }

    /**
     * 计算两个时间间隔的天数
     *
     * @param firstDate 小者
     * @param lastDate  大者
     * @return int 默认-1
     */
    public static int getIntervalDays(Date firstDate, Date lastDate) {
        if (null == firstDate || null == lastDate) {
            return -1;
        }

        long intervalMilli = lastDate.getTime() - firstDate.getTime();
        return (int) (intervalMilli / (24 * 60 * 60 * 1000));
    }

    /**
     * 计算两个日期间隔的天数，间隔只关心日期
     *
     * @param firstDate
     * @param lastDate
     * @return
     */
    public static int daysOfTwo(Date firstDate, Date lastDate) {
        return getIntervalDays(firstDate, lastDate);
    }

    /**
     * 计算两个日期间隔的小时数
     *
     * @param firstDate 小者
     * @param lastDate  大者
     * @return int 默认-1
     */
    public static int getTimeIntervalHours(Date firstDate, Date lastDate) {
        if (null == firstDate || null == lastDate) {
            return -1;
        }

        long intervalMilli = lastDate.getTime() - firstDate.getTime();
        return (int) (intervalMilli / (60 * 60 * 1000));
    }

    /**
     * 计算两个日期间隔的分钟数
     *
     * @param firstDate 小者
     * @param lastDate  大者
     * @return int 默认-1
     */
    public static int getTimeIntervalMins(Date firstDate, Date lastDate) {
        if (null == firstDate || null == lastDate) {
            return -1;
        }

        long intervalMilli = lastDate.getTime() - firstDate.getTime();
        return (int) (intervalMilli / (60 * 1000));
    }

    /**
     * format the date in given pattern 格式化日期
     *
     * @param d       date
     * @param pattern time pattern
     * @return the formatted string
     */
    public static String formatDatetoString(Date d, String pattern) {
        if (null == d || StringUtils.isEmpty(pattern)) {
            return null;
        }

        SimpleDateFormat formatter = (SimpleDateFormat) DateFormat.getDateInstance();

        formatter.applyPattern(pattern);
        return formatter.format(d);
    }

    public static Date formatDate(Date d, String pattern) {
        return DateUtil.getDateFromString(formatDatetoString(d, pattern));
    }

    /**
     * 比较两个日期的先后顺序
     *
     * @param src
     * @param desc
     * @return
     */
    public static int compareDate(Date src, Date desc) {
        if ((src == null) && (desc == null)) {
            return EQUAL;
        } else if (desc == null) {
            return BEFORE;
        } else if (src == null) {
            return AFTER;
        } else {
            long timeSrc = src.getTime();
            long timeDesc = desc.getTime();

            if (timeSrc == timeDesc) {
                return EQUAL;
            } else {
                return (timeDesc > timeSrc) ? AFTER : BEFORE;
            }
        }
    }

    /**
     * 比较两个日期的先后顺序
     *
     * @param first  date1
     * @param second date2
     * @return EQUAL - if equal BEFORE - if before than date2 AFTER - if over
     * than date2
     */
    public static int compareTwoDate(Date first, Date second) {
        if ((first == null) && (second == null)) {
            return EQUAL;
        } else if (first == null) {
            return BEFORE;
        } else if (second == null) {
            return AFTER;
        } else if (first.before(second)) {
            return BEFORE;
        } else if (first.after(second)) {
            return AFTER;
        } else {
            return EQUAL;
        }
    }

    /**
     * 比较日期是否介于两者之间
     *
     * @param date  the specified date
     * @param begin date1
     * @param end   date2
     * @return true - between date1 and date2 false - not between date1 and
     * date2
     */
    public static boolean isDateBetween(Date date, Date begin, Date end) {
        int c1 = compareTwoDate(begin, date);
        int c2 = compareTwoDate(date, end);

        return (((c1 == BEFORE) && (c2 == BEFORE)) || (c1 == EQUAL) || (c2 == EQUAL));
    }

    /**
     * 比较日期是否介于当前日期的前后数天内
     *
     * @param myDate
     * @param begin
     * @param end
     * @return
     */
    public static boolean isDateBetween(Date myDate, int begin, int end) {
        return isDateBetween(myDate, getCurrentDateTime(), begin, end);
    }

    /**
     * 比较日期是否介于指定日期的前后数天内
     *
     * @param utilDate
     * @param dateBaseLine
     * @param begin
     * @param end
     * @return
     */
    public static boolean isDateBetween(Date utilDate, Date dateBaseLine, int begin, int end) {
        String pattern = TIME_PATTERN;

        String my = toString(utilDate, pattern);
        Date myDate = toDate(my, pattern);

        String baseLine = toString(dateBaseLine, pattern);

        // Date baseLineDate = parseString2Date(baseLine, pattern);
        String from = addDays(baseLine, begin);
        Date fromDate = toDate(from, pattern);

        String to = addDays(baseLine, end);
        Date toDate = toDate(to, pattern);

        return isDateBetween(myDate, fromDate, toDate);
    }

    /**
     * 增加天数
     *
     * @param date
     * @param day
     * @return Date
     */
    public static Date addDate(Date date, int day) {
        if (null == date) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + day);
        return calendar.getTime();
    }

    /**
     * 增加月数
     *
     * @param date
     * @param month 需要增加的月数，比如需要增加2个月，就传入2
     * @return
     */

    public static Date addMonth(Date date, int month) {
        if (null == date) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (month != 0) {
            calendar.add(Calendar.MONTH, month);
        }
        return calendar.getTime();
    }

    /**
     * 增加小时
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date addHour(Date date, int hour) {
        if (null == date) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
        return calendar.getTime();
    }

    /**
     * 增加天数
     *
     * @param date
     * @param day
     * @param pattern
     * @return
     */
    public static String addDays(Date date, int day, String pattern) {
        return addDays(toString(date, pattern), day, pattern);
    }

    /**
     * 增加天数
     *
     * @param date
     * @param day
     * @return
     */
    public static String addDays(Date date, int day) {
        return addDays(toString(date, TIME_PATTERN), day);
    }

    /**
     * 增加天数
     *
     * @param date
     * @param day
     * @return
     */
    public static String addDays(String date, int day) {
        return addDays(date, day, TIME_PATTERN);
    }

    /**
     * get the time of the specified date after given days
     *
     * @param date the specified date
     * @param day  day distance
     * @return the format string of time
     */
    public static String addDays(String date, int day, String pattern) {
        if (date == null) {
            return "";
        }

        if (date.equals("")) {
            return "";
        }

        if (day == 0) {
            return date;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            Calendar calendar = dateFormat.getCalendar();

            calendar.setTime(dateFormat.parse(date));
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + day);
            return dateFormat.format(calendar.getTime());
        } catch (Exception ex) {
            LogUtils.e(TAG, ex.toString());
            return "";
        }
    }

    public static Date addDaysByDate(Date date, int day) {
        if (date == null) {
            return null;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_PATTERN);
            Calendar calendar = dateFormat.getCalendar();

            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + day);
            return calendar.getTime();
        } catch (Exception ex) {
            LogUtils.e(TAG, ex.toString());
            return null;
        }
    }


    /**
     * return current date
     *
     * @return current date
     */
    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 获取当前天，不计时分秒
     *
     * @return
     * @author lihh
     * @date 2014年12月8日
     */
    public static Date getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE));
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        return calendar.getTime();
    }

    /**
     * return current calendar instance
     *
     * @return Calendar
     */
    public static Calendar getCurrentCalendar() {
        return Calendar.getInstance();
    }

    /**
     * return current time
     *
     * @return current time
     */
    public static Timestamp getCurrentDateTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取年份
     *
     * @param date Date
     * @return int
     */
    public static final int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取年份
     *
     * @param millis long
     * @return int
     */
    public static final int getYear(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月份
     *
     * @param date Date
     * @return int
     */
    public static final int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取月份
     *
     * @param millis long
     * @return int
     */
    public static final int getMonth(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期
     *
     * @param date Date
     * @return int
     */
    public static final int getDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取小时
     *
     * @param date Date
     * @return int
     */
    public static final int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取小时
     *
     * @param millis long
     * @return int
     */
    public static final int getHour(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 把日期后的时间归0 变成(yyyy-MM-dd 00:00:00:000)
     *
     * @param fullDate Date
     * @return Date
     */
    public static final Date zerolizedTime(Date fullDate) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(fullDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 把日期的时间变成(yyyy-MM-dd 23:59:59:999)
     *
     * @param date
     * @return
     */
    public static final Date getEndTime(Date date) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public static String getTimeFrom(Date date) {
        String format = "";
        if (date != null) {
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            format = sim.format(date);
        }
        return format;
    }

    /**
     * 获取日期对应周一的时间
     *
     * @param date
     * @return
     */
    public static Date getMondayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return zerolizedTime(calendar.getTime());
    }

    /**
     * 获取周一的时间
     *
     * @return
     */
    public static Date getMondayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return zerolizedTime(calendar.getTime());
    }

    /**
     * 获取日期对应周日的时间
     *
     * @param date
     * @return
     */
    public static Date getSundayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return zerolizedTime(calendar.getTime());
    }

    /**
     * 获取周日的时间
     *
     * @return
     */
    public static Date getSundayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return zerolizedTime(calendar.getTime());
    }

    /**
     * 根据传入的年月日组装成string日期yyyy-MM-dd
     *
     * @return
     */
    public static String getStringDateByYMD(int year, int month, int day) {
        return year + "-" + month + "-" + day;
    }

    /**
     * 返回指定时间的后面一天
     *
     * @param date
     * @return
     */
    public static Date getNextDay(String date) {
        return getDateFromString(addDays(date, 1));
    }

    /**
     * 返回指定时间的后面一天
     *
     * @param second
     * @return
     */
    public static Date getNextDay(long second) {
        if (second == 0) {
            return null;
        }
        return toDate(addDays(getDate(second), 1), TIME_PATTERN);
    }

    /**
     * 给返回时间加上空值判断
     *
     * @param date
     * @return
     */
    public static Long getTime(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }

    /**
     * 给返回的日期加上空值判断
     *
     * @param second
     * @return
     */
    public static Date getDate(long second) {
        if (second == 0) {
            return null;
        } else {
            return new Date(second * 1000);
        }
    }

    /**
     * 比较两个时间是否同一天
     *
     * @param begin
     * @param end
     * @return
     */
    public static boolean isTheSameDay(Date begin, Date end) {
        String beginDate = DateUtil.formatDatetoString(begin, "yyyy-MM-dd");
        String endDate = DateUtil.formatDatetoString(end, "yyyy-MM-dd");
        return beginDate.equals(endDate);
    }

    /**
     * <pre>
     * 转10位时间截
     *
     * @return </pre>
     */
    public static String timestampDate(Date date) {
        String time = DateUtil.toString(date, DateUtil.TIME_PATTERN);
        String dateline = time + "";
        dateline = dateline.substring(0, 10);
        long stampDate = DateUtil.toDate(dateline, DateUtil.DATE_FMT_3).getTime();
        return String.valueOf(stampDate).substring(0, 10);
    }

    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == 1 ? 7 : (dayOfWeek - 1);
    }

    /**
     * 时间比较
     *
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static int compareDate(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat(TIME_PATTERN);
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("DATE1在DATE2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("DATE1在DATE2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 根据传入的时间，得到date当天中 + suffix '08:08:08'
     * 如
     * date="2015-12-01 01:10:59"
     * return "2015-12-01 08:08:08"
     *
     * @param date   时间
     * @param suffix 时间后缀 如“23:59:59” 或者“23:59:59”
     * @return
     */
    public static Date getCurrentDateSuffixDate(Date date, String suffix) {
        return DateUtil.toDate(DateUtil.formatDatetoString(date, DateUtil.TIME_PATTERN).substring(0, 10) + " " + suffix, DateUtil.TIME_PATTERN);
    }




}

