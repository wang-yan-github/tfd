package com.system.tool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.system.global.KeyConst;
import com.system.global.SysConst;

public class SysTool {
    // log
    private static Logger log = null;
    // 三个一分组，保留两位小数

    public static final int WITHGROUP = 0;
    // 不分组，保留两位小数
    public static final int WITHOUTGROUP = 1;

    // 数字串的格式（在多用户的环境中使用静态是不合适的）

    private static DecimalFormat numFormat = new DecimalFormat("#0.00");
    private static DecimalFormat numFormatG = new DecimalFormat("#,##0.00");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat dateFormatS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    private static SimpleDateFormat dateFormatSR = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    public static String DATE_FORMAT_NOSPLIT = "yyyyMMddHHmmssSSS";
    private static SimpleDateFormat dateFormatEn = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    private static SimpleDateFormat dateFormatCn = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINESE);
    private static SimpleDateFormat dateFormatYMEn = new SimpleDateFormat("MMM-yyyy", Locale.ENGLISH);
    private static SimpleDateFormat dateFormatYMCn = new SimpleDateFormat("yyyy年MM月", Locale.CHINESE);
    private static SimpleDateFormat dateFormatYMCn1 = new SimpleDateFormat("yyyyMM", Locale.CHINESE);
    private static SimpleDateFormat dateFormatMDEn = new SimpleDateFormat("dd-MMM", Locale.ENGLISH);
    private static SimpleDateFormat dateFormatMDCn = new SimpleDateFormat("MM-dd", Locale.CHINESE);

    private static final String[] enSmallNumber = { "", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX",
            "SEVEN", "EIGHT", "NINE", "TEN", "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN", "FIFTEEN",
            "SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINETEEN" };
    private static final String[] enLargeNumber = { "TWENTY", "THIRTY", "FORTY", "FIFTY", "SIXTY", "SEVENTY",
            "EIGHTY", "NINETY" };
    private static final String[] enUnit = { "", "THOUSAND", "MILLION", "BILLION", "TRILLION" };

    /**
     * 判断是否为空串
     * 
     * @param str
     * @return true: null/"" false: 其它
     */
    public static boolean isNullorEmpty(String str) {
        if (str == null) {
            return true;
        }
        str = str.trim();
        if (str.length() < 1) {
            return true;
        }
        return false;
    }

    /**
     * 处理空值的字符串
     * 
     * @param str
     * @return
     */
    public static String nullToEmpty(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    /**
     * 处理空值的字符串
     * 
     * @param str
     *            源字符串
     * @param defaultStr
     *            默认字符串
     * 
     * @return
     */
    public static String empty2Default(String str, String defaultStr) {

        if (isNullorEmpty(str)) {
            return defaultStr;
        }
        return str;
    }

    /**
     * 取得格式化后的字符串
     * 
     * @param num
     * @return
     */
    public static String getFormatedStr(BigDecimal num, int pattern) {
        if (pattern == WITHOUTGROUP) {
            return numFormat.format(num);
        }
        return numFormatG.format(num);
    }

    /**
     * 取得格式化后的字符串
     * 
     * @param num
     * @return
     */
    public static String getFormatedStr(double num, int pattern) {
        if (pattern == WITHOUTGROUP) {
            return numFormat.format(num);
        }
        return numFormatG.format(num);
    }

    /**
     * 取得格式化后的字符串
     * 
     * @param num
     * @return
     */
    public static String getFormatedStr(String numStr, int pattern) {
        String rtStr = "0.00";
        try {
            double dValue = parseDouble(numStr);
            if (pattern == WITHOUTGROUP) {
                rtStr = numFormat.format(dValue);
            } else {
                rtStr = numFormatG.format(dValue);
            }
        } catch (Exception ex) { // 非数字

        }
        return rtStr;
    }

    /**
     * 取得当前的时间，格式为 yyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public static String getCurDateTimeStr() {
        return dateFormat.format(new Date());
    }

    public static String getCurDateTimeStr(String pattern) {
        if (pattern == null) {
            return getCurDateTimeStr();
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date());
    }

    /**
     * 取得当前的时间，格式为 yyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public static String getCurDateTimeStr(SimpleDateFormat format) {
        if (format == null) {
            return getCurDateTimeStr();
        }
        return format.format(new Date());
    }

    /**
     * 取得的时间串，格式为 yyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public static String getDateTimeStr(Date date) {
        if (date == null) {
            return getCurDateTimeStr();
        }
        return dateFormat.format(date);
    }

    /**
     * 取得的英文习惯的时间串，格式为 dd-MMM-yyyy
     * 
     * @return
     */
    public static String getDateTimeStrEn(Date date) {
        if (date == null) {
            return getCurDateTimeStr(dateFormatEn);
        }
        return dateFormatEn.format(date);
    }

    /**
     * 取得的中文习惯的时间串，格式为 yyyy年MM月dd日
     * 
     * @return
     */
    public static String getDateTimeStrCn(Date date) {
        if (date == null) {
            return getCurDateTimeStr(dateFormatCn);
        }
        return dateFormatCn.format(date);
    }

    /**
     * 取得的英文习惯的时间串，格式为 dd-MMM-yyyy
     * 
     * @return
     */
    public static String getDateTimeStrEn(String dateStr) throws Exception {
        if (dateStr == null) {
            return getCurDateTimeStr(dateFormatEn);
        }
        return getDateTimeStrEn(parseDate(dateStr));
    }

    /**
     * 取得的中文习惯的时间串，格式为 yyyy年MM月dd日
     * 
     * @return
     */
    public static String getDateTimeStrCn(String dateStr) throws Exception {
        if (dateStr == null) {
            return getCurDateTimeStr(dateFormatCn);
        }
        return getDateTimeStrCn(parseDate(dateStr));
    }

    /**
     * 取得的英文习惯的时间串，格式为 dd-MMM-yyyy
     * 
     * @return
     */
    public static String getDateTimeStrYMEn(Date date) {
        if (date == null) {
            return getCurDateTimeStr(dateFormatYMEn);
        }
        return dateFormatYMEn.format(date);
    }

    /**
     * 取得的中文习惯的时间串，格式为 yyyy年MM月dd日
     * 
     * @return
     */
    public static String getDateTimeStrYMCn(Date date) {
        if (date == null) {
            return getCurDateTimeStr(dateFormatYMCn);
        }
        return dateFormatYMCn.format(date);
    }

    /**
     * 取得的中文习惯的时间串，格式为 yyyydd
     * 
     * @return
     */
    public static String getDateTimeStrYMCn1(Date date) {
        if (date == null) {
            return getCurDateTimeStr(dateFormatYMCn1);
        }
        return dateFormatYMCn1.format(date);
    }

    /**
     * 取得的中文习惯的时间串，格式为 yyyymmddhhMMssSSS
     * 
     * @return
     */
    public static String getDateTimeStrSR(Date date) {
        if (date == null) {
            return getCurDateTimeStr(dateFormatSR);
        }
        return dateFormatSR.format(date);
    }

    /**
     * 取得的英文习惯的时间串，格式为 dd-MMM-yyyy
     * 
     * @return
     */
    public static String getDateTimeStrYMEn(String dateStr) throws Exception {
        if (dateStr == null) {
            return getCurDateTimeStr(dateFormatYMEn);
        }
        return getDateTimeStrYMEn(parseDate(dateStr));
    }

    /**
     * 取得的中文习惯的时间串，格式为 yyyy年MM月dd日
     * 
     * @return
     */
    public static String getDateTimeStrYMCn(String dateStr) throws Exception {
        if (dateStr == null) {
            return getCurDateTimeStr(dateFormatYMCn);
        }
        return getDateTimeStrYMCn(parseDate(dateStr));
    }

    /**
     * 取得的英文习惯的时间串，格式为 dd-MMM-yyyy
     * 
     * @return
     */
    public static String getDateTimeStrMDEn(Date date) {
        if (date == null) {
            return getCurDateTimeStr(dateFormatMDEn);
        }
        return dateFormatMDEn.format(date);
    }

    /**
     * 取得的中文习惯的时间串，格式为 yyyy年MM月dd日
     * 
     * @return
     */
    public static String getDateTimeStrMDCn(Date date) {
        if (date == null) {
            return getCurDateTimeStr(dateFormatMDCn);
        }
        return dateFormatMDCn.format(date);
    }

    /**
     * 取得的英文习惯的时间串，格式为 dd-MMM-yyyy
     * 
     * @return
     */
    public static String getDateTimeStrMDEn(String dateStr) throws Exception {
        if (dateStr == null) {
            return getCurDateTimeStr(dateFormatMDEn);
        }
        return getDateTimeStrMDEn(parseDate(dateStr));
    }

    /**
     * 取得的中文习惯的时间串，格式为 yyyy年MM月dd日
     * 
     * @return
     */
    public static String getDateTimeStrMDCn(String dateStr) throws Exception {
        if (dateStr == null) {
            return getCurDateTimeStr(dateFormatMDCn);
        }
        return getDateTimeStrMDCn(parseDate(dateStr));
    }

    /**
     * Date清零
     * 
     * @param date
     * @param clearNum
     *            1=毫秒, 2=秒, 3=分钟, 4=小时, 5=天, 6=月份
     * @return
     */
    private static Calendar clearDate(Date date, int clearNum) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        // 毫秒
        if (clearNum > 0) {
            cal.set(Calendar.MILLISECOND, 0);
        }
        // 秒

        if (clearNum > 1) {
            cal.set(Calendar.SECOND, 0);
        }
        // 分钟
        if (clearNum > 2) {
            cal.set(Calendar.MINUTE, 0);
        }
        // 小时
        if (clearNum > 3) {
            cal.set(Calendar.HOUR_OF_DAY, 0);
        }
        // 天

        if (clearNum > 4) {
            cal.set(Calendar.DATE, 0);
        }
        // 月份
        if (clearNum > 5) {
            cal.set(Calendar.MONTH, 0);
        }
        return cal;
    }

    /**
     * 取得指定日期的起始时间串
     * 
     * @param date
     * @return
     */
    public static String[] getDateLimitStr() throws Exception {
        return getDateLimitStr(new Date());
    }

    /**
     * 取得指定日期当周的起始时间串
     * 
     * @param date
     * @return
     */
    public static String[] getWeekLimitStr() throws Exception {
        return getWeekLimitStr(new Date());
    }

    /**
     * 取得指定日期当月的起始时间串
     * 
     * @param date
     * @return
     */
    public static String[] getMonthLimitStr() throws Exception {
        return getMonthLimitStr(new Date());
    }

    /**
     * 取得指定日期当年的起始时间串
     * 
     * @param date
     * @return
     */
    public static String[] getYearLimitStr() throws Exception {
        return getYearLimitStr(new Date());
    }

    /**
     * 取得指定日期的起始时间串
     * 
     * @param date
     * @return
     */
    public static String[] getDateLimitStr(Date date) throws Exception {
        Date[] rtDateArray = getDateLimit(date);
        return new String[] { getDateTimeStr(rtDateArray[0]), getDateTimeStr(rtDateArray[1]) };
    }

    /**
     * 取得指定日期当周的起始时间串
     * 
     * @param date
     * @return
     */
    public static String[] getWeekLimitStr(Date date) throws Exception {
        Date[] rtDateArray = getWeekLimit(date);
        return new String[] { getDateTimeStr(rtDateArray[0]), getDateTimeStr(rtDateArray[1]) };
    }

    /**
     * 取得指定日期当月的起始时间串
     * 
     * @param date
     * @return
     */
    public static String[] getMonthLimitStr(Date date) throws Exception {
        Date[] rtDateArray = getMonthLimit(date);
        return new String[] { getDateTimeStr(rtDateArray[0]), getDateTimeStr(rtDateArray[1]) };
    }

    /**
     * 取得指定日期当年的起始时间串
     * 
     * @param date
     * @return
     */
    public static String[] getYearLimitStr(Date date) throws Exception {
        Date[] rtDateArray = getYearLimit(date);
        return new String[] { getDateTimeStr(rtDateArray[0]), getDateTimeStr(rtDateArray[1]) };
    }

    public static Date getDateByStr(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 取得指定日期的起始时间
     * 
     * @param date
     * @return
     */
    public static Date[] getDateLimit(Date date) throws Exception {
        Calendar cal = clearDate(date, 4);
        Date date1 = cal.getTime();

        cal.add(Calendar.DATE, 1);
        cal.add(Calendar.SECOND, -1);
        Date date2 = cal.getTime();

        return new Date[] { date1, date2 };
    }

    /**
     * 取得当前日期所在周的第一天
     * 
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = clearDate(date, 4);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime();
    }

    /**
     * 取得当前日期所在周的最后一天
     * 
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = clearDate(date, 4);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 000);
        return c.getTime();
    }

    /**
     * 取得指定日期的当周的起始时间
     * 
     * @param date
     * @return
     */
    public static Date[] getWeekLimit(Date date) throws Exception {
        Date date1 = getFirstDayOfWeek(date);
        Date date2 = getLastDayOfWeek(date);
        return new Date[] { date1, date2 };
    }

    /**
     * 取得指定日期的当月起始时间
     * 
     * @param date
     * @return
     */
    public static Date[] getMonthLimit(Date date) throws Exception {
        Calendar cal = clearDate(date, 5);
        cal.set(Calendar.DATE, 1);
        Date date1 = cal.getTime();

        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.SECOND, -1);
        Date date2 = cal.getTime();

        return new Date[] { date1, date2 };
    }

    /**
     * 取得指定日期的当年起始时间
     * 
     * @param date
     * @return
     */
    public static Date[] getYearLimit(Date date) throws Exception {
        Calendar cal = clearDate(date, 6);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 1);
        Date date1 = cal.getTime();

        cal.add(Calendar.YEAR, 1);
        cal.add(Calendar.SECOND, -1);
        Date date2 = cal.getTime();

        return new Date[] { date1, date2 };
    }

    /**
     * 取得天数间隔
     * 
     * @param fromDate
     * @param toDate
     * @return
     */
    public static int getDaySpan(String toDateStr) throws Exception {
        return (int) ((parseDate(toDateStr).getTime() - new Date().getTime()) / SysConst.DT_D);
    }

    /**
     * 取得天数间隔
     * 
     * @param fromDate
     * @param toDate
     * @return
     */
    public static int getDaySpan(Date toDate) {
        return (int) ((toDate.getTime() - new Date().getTime()) / SysConst.DT_D);
    }

    /**
     * 取得天数间隔
     * 
     * @param fromDate
     * @param toDate
     * @return
     */
    public static int getDaySpan(Date fromDate, Date toDate) {
        return (int) ((toDate.getTime() - fromDate.getTime()) / SysConst.DT_D);
    }

    /**
     * 取得前一天的时间
     * 
     * @param dateStr
     * @return
     */
    public static Date getDayBefore(String dateStr, int dayCnt) throws Exception {
        return getDayBefore(parseDate(dateStr), dayCnt);
    }

    /**
     * 取得前一天的时间
     * 
     * @param date
     * @return
     */
    public static Date getDayBefore(Date date, int dayCnt) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, 0 - dayCnt);
        return cal.getTime();
    }

    /**
     * 取得后一天的时间字
     * 
     * @param dateStr
     * @return
     */
    public static Date getDayAfter(String dateStr, int dayCnt) throws Exception {
        return getDayAfter(parseDate(dateStr), dayCnt);
    }

    /**
     * 取得后一天的时间
     * 
     * @param date
     * @return
     */
    public static Date getDayAfter(Date date, int dayCnt) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, dayCnt);
        return cal.getTime();
    }

    /**
     * 取得指定天数差的时间字
     * 
     * @param dateStr
     * @return
     */
    public static Date getDayDiff(String dateStr, int dayCnt) throws Exception {
        return getDayDiff(parseDate(dateStr), dayCnt);
    }

    /**
     * 取得指定天数差的时间
     * 
     * @param date
     * @return
     */
    public static Date getDayDiff(Date date, int dayCnt) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, dayCnt);
        return cal.getTime();
    }

    /**
     * 取得前一天的时间字
     * 
     * @param dateStr
     * @return
     */
    public static Date getYestday(String dateStr) throws Exception {
        return getYestday(parseDate(dateStr));
    }

    /**
     * 取得前一天的时间
     * 
     * @param date
     * @return
     */
    public static Date getYestday(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * 取得前一天的时间字符串
     * 
     * @param dateStr
     * @return
     */
    public static String getYestdayStr(String dateStr) throws Exception {
        return getYestdayStr(parseDate(dateStr));
    }

    /**
     * 取得前一天的时间字符串
     * 
     * @param date
     * @return
     */
    public static String getYestdayStr(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        return getDateTimeStr(cal.getTime());
    }

    /**
     * 取得前一月的时间字符串
     * 
     * @param date
     * @return
     */
    public static String getMonthBefore(String dateStr, int diff) throws Exception {
        return getMonthBefore(parseDate(dateStr), diff);
    }

    /**
     * 取得前一月的时间字符串
     * 
     * @param date
     * @return
     */
    public static String getMonthBefore(Date date, int diff) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 0 - diff);
        return getDateTimeStr(cal.getTime());
    }

    /**
     * 取得前一月的时间字符串
     * 
     * @param date
     * @return
     */
    public static String getMonthAfter(String dateStr, int diff) throws Exception {
        return getMonthAfter(parseDate(dateStr), diff);
    }

    /**
     * 取得前一月的时间字符串
     * 
     * @param date
     * @return
     */
    public static String getMonthAfter(Date date, int diff) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 0 + diff);
        return getDateTimeStr(cal.getTime());
    }

    /**
     * 从以“逗号”分隔的数串取得double类型的值
     * 
     * @param numStr
     * @return
     */
    public static double getDoubleFromStr(String numStr) {
        double rtValue = 0.00d;
        try {
            rtValue = numFormatG.parse(numStr).doubleValue();
        } catch (Exception ex) {
        }
        return rtValue;
    }

    /**
     * 从以“逗号”分隔的数串取得long类型的值
     * 
     * @param numStr
     * @return
     */
    public static long getLongFromStr(String numStr) {
        long rtValue = 0;
        try {
            rtValue = numFormatG.parse(numStr).longValue();
        } catch (Exception ex) {
        }
        return rtValue;
    }

    /**
     * 从以“逗号”分隔的数串取得int类型的值
     * 
     * @param numStr
     * @return
     */
    public static int getIntFromStr(String numStr) {
        int rtValue = 0;
        try {
            rtValue = numFormatG.parse(numStr).intValue();
        } catch (Exception ex) {
        }
        return rtValue;
    }

    /**
     * 取得文件的精确的大小，单位是字节
     * 
     * @return
     */
    public static long getFileSize(String fileName) {
        long rtSize = 0;
        File file = new File(fileName);
        rtSize = file.length();
        return rtSize;
    }

    public static String getRequestPath(String uri) {
        String rtStr = null;
        int tmpIndex = uri.lastIndexOf("/");
        if (tmpIndex >= 0) {
            rtStr = uri.substring(0, tmpIndex);
        }
        return rtStr;
    }

    /**
     * 取得指定长度的数字
     * 
     * @param str
     *            原始的数串
     * 
     * @param length
     *            长度
     * @return length <= str.length str length > str.length 字符串前面补零到指定长度的字符串
     */
    public static String getFixLengthNum(String str, int length) {
        if (str == null) {
            str = "";
        }
        String preFix = "";
        for (int i = 0; i < length - str.length(); i++) {
            preFix += "0";
        }
        return preFix + str;
    }

    /**
     * 10进制转换为36进制字符串
     * 
     * @param num
     *            10进制数字
     * @param length
     *            返回字符串长度
     * 
     * @return 36进制字符串
     * 
     * @throws Exception
     */
    public static String get36BaseStr(long num, int length) throws Exception {
        String numStr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        long[] tmpArray = get10ArrayFromNum(num, 36, new long[] {});
        String tmpStr = "";
        for (int i = 0; i < tmpArray.length; i++) {
            tmpStr += String.valueOf(numStr.charAt((int) tmpArray[i]));
        }
        String rtStr = getFixLengthNum(tmpStr, length);
        return rtStr;
    }

    /**
     * 把任意进制的数转换为十进制数的数组
     * 
     * @param num
     *            十进制表示的任意进制的数
     * @param baseNum
     *            基数
     * @param num10
     * @return
     * @throws java.lang.Exception
     */
    public static long[] get10ArrayFromNum(long num, int baseNum, long[] num10) throws Exception {
        long[] tmpArray = new long[num10.length + 1];
        tmpArray[0] = num % baseNum;
        if (num10.length > 0) {
            System.arraycopy(num10, 0, tmpArray, 1, num10.length);
        }
        if (num / baseNum == 0) {
            return tmpArray;
        } else {
            return get10ArrayFromNum(num / baseNum, baseNum, tmpArray);
        }
    }

    /**
     * 把字符串前面补“0”到length长度
     * 
     * @param str
     *            需要补足位数的字符串
     * 
     * @param length
     *            返回字符串的长度
     * @return str是null： null str的长度小于length： 前面补“0”到length长度的字符串
     *         str的长度大于或者等于length： str本身
     */
    public static String getFixLengthStringFront(String str, int length) {
        String rtStr = str;
        if (rtStr == null) {
            return rtStr;
        }
        if (rtStr.length() >= length) {
            return rtStr;
        }
        for (int i = rtStr.length(); i < length; i++) {
            rtStr = "0" + rtStr;
        }
        return rtStr;
    }

    /**
     * 把字符串后面补“0”到length长度
     * 
     * @param str
     *            需要补足位数的字符串
     * 
     * @param length
     *            返回字符串的长度
     * @return str是null： null str的长度小于length： 后面补“0”到length长度的字符串
     *         str的长度大于或者等于length： str本身
     */
    public static String getFixLengthStringPost(String str, int length) {
        String rtStr = str;
        if (rtStr == null) {
            return rtStr;
        }
        if (rtStr.length() >= length) {
            return rtStr;
        }
        for (int i = rtStr.length(); i < length; i++) {
            rtStr += "0";
        }
        return rtStr;
    }

    /**
     * 把金额格式化为英文大写
     * 
     * @param val
     *            金额数
     * 
     * @return
     */
    public static String number2En(double val) {
        String moneyString = getFormatedStr(val, WITHOUTGROUP);
        String[] tmpString = moneyString.split("\\.");
        // 默认为整数

        String intString = moneyString;
        // 保存小数部分字串
        String decString = "";
        // 保存英文大写字串
        String engCapital = "";
        StringBuffer strBuff1 = null;
        StringBuffer strBuff2 = null;
        StringBuffer strBuff3 = null;
        int curPoint = 0;
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        int k = 0;
        int n = 0;

        if (tmpString.length > 1) {
            intString = tmpString[0]; // 取整数部分

            decString = tmpString[1]; // 取小数部分

        }
        decString += "00";
        decString = decString.substring(0, 2); // 保留两位小数位

        try {
            // 以下处理整数部分
            curPoint = intString.length() - 1;
            if (curPoint >= 0 && curPoint < 15) {
                k = 0;
                while (curPoint >= 0) {
                    strBuff1 = new StringBuffer("");
                    strBuff2 = new StringBuffer("");
                    strBuff3 = new StringBuffer("");
                    if (curPoint >= 2) {
                        // n = Integer.parseInt(intString.substring(curPoint -
                        // 2, 3));
                        n = Integer.parseInt(intString.substring(curPoint - 2, curPoint + 1));
                        if (n != 0) {
                            i1 = n / 100; // 取佰位数值

                            i2 = (n - i1 * 100) / 10; // 取拾位数值

                            i3 = n - i1 * 100 - i2 * 10; // 取个位数值

                            if (i1 != 0) {
                                strBuff1.append(enSmallNumber[i1] + " HUNDRED ");
                            }
                            if (i2 != 0) {
                                if (i2 == 1) {
                                    strBuff2.append(enSmallNumber[i2 * 10 + i3] + " ");
                                } else {
                                    strBuff2.append(enLargeNumber[i2 - 2] + " ");
                                    if (i3 != 0) {
                                        strBuff3.append(enSmallNumber[i3] + " ");
                                    }
                                }
                            } else {
                                if (i3 != 0) {
                                    strBuff3.append(enSmallNumber[i3] + " ");
                                }
                            }
                            engCapital = strBuff1.toString() + strBuff2.toString() + strBuff3.toString()
                                    + enUnit[k] + " " + engCapital;
                        }
                    } else {
                        n = Integer.parseInt(intString.substring(0, curPoint + 1));
                        if (n != 0) {
                            i2 = n / 10; // 取拾位数值

                            i3 = n - i2 * 10; // 取个位数值

                            if (i2 != 0) {
                                if (i2 == 1) {
                                    strBuff2.append(enSmallNumber[i2 * 10 + i3] + " ");
                                } else {
                                    strBuff2.append(enLargeNumber[i2 - 2] + " ");
                                    if (i3 != 0) {
                                        strBuff3.append(enSmallNumber[i3] + " ");
                                    }
                                }
                            } else {
                                if (i3 != 0) {
                                    strBuff3.append(enSmallNumber[i3] + " ");
                                }
                            }
                            engCapital = strBuff2.toString() + strBuff3.toString() + enUnit[k] + " "
                                    + engCapital;
                        }
                    }

                    ++k;
                    curPoint -= 3;
                }
                engCapital = engCapital.trim();
            }

            // 以下处理小数部分
            strBuff2 = new StringBuffer();
            strBuff3 = new StringBuffer();
            n = Integer.parseInt(decString);
            if (n != 0) {
                i2 = n / 10; // 取拾位数值

                i3 = n - i2 * 10; // 取个位数值

                if (i2 != 0) {
                    if (i2 == 1) {
                        strBuff2.append(enSmallNumber[i2 * 10 + i3] + " ");
                    } else {
                        strBuff2.append(enLargeNumber[i2 - 2] + " ");
                        if (i3 != 0) {
                            strBuff3.append(enSmallNumber[i3] + " ");
                        }
                    }
                } else {
                    if (i3 != 0) {
                        strBuff3.append(enSmallNumber[i3] + " ");
                    }
                }

                // 将小数字串追加到整数字串后

                if (engCapital.length() > 0) {
                    engCapital = engCapital + " AND CENTS " + strBuff2 + strBuff3; // 有整数部分时
                } else {
                    engCapital = "CENTS " + strBuff2 + strBuff3; // 只有小数部分时

                }
            }

            engCapital = engCapital.trim();
            return engCapital;
        } catch (Exception ex) {
            ex.printStackTrace();
            return ""; // 含非数字字符时，返回零长字串
        }
    }

    /**
     * 判断是否是数字
     * 
     * 
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        return Pattern.matches("-?\\d*\\.?\\d+(E\\d+)?", str);
    }

    /**
     * 判断是否是金额数字
     * 
     * @param str
     * @return
     */
    public static boolean isAmtNumber(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        return Pattern.matches("-?\\d{1,3}(\\,\\d{3})*\\.\\d{0,8}", str);
    }

    /**
     * 判断是否是数字
     * 
     * 
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        return Pattern.matches("(-?[1-9]\\d*)|0", str);
    }

    /**
     * 判断是否是数字
     * 
     * 
     * @param str
     * @return
     */
    public static boolean isPositiveInteger(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        return Pattern.matches("[1-9]\\d*", str);
    }

    /**
     * 判断是否符合帐务年月的格式
     * 
     * 
     * @param str
     * @return
     */
    public static boolean isAcctYMFormat(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        return Pattern.matches("\\d{4}-\\d{2}", str);
    }

    /**
     * 判断是否符合系统规定的日期格式
     * 
     * @param str
     * @return
     */
    public static boolean isSysDateFormat(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        if (str.length() < 10) {
            return false;
        }
        if (str.length() == 10) {
            return Pattern.matches("\\d{4}-\\d{2}-\\d{2}", str);
        } else {
            return Pattern.matches("\\d{4}-\\d{2}-\\d{2}\\s{1}\\d{2}:\\d{2}:\\d{2}", str);
        }
    }

    /**
     * 判断是否符合系统规定的日期格式
     * 
     * @param str
     * @return
     */
    public static boolean isDay(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        if (str.length() < 10) {
            return false;
        }
        return Pattern.matches("\\d{4}-\\d{2}-\\d{2}", str);
    }

    /**
     * 判断是否符合系统规定的日期格式
     * 
     * @param str
     * @return
     */
    public static boolean isDayTime(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        if (str.length() < 19) {
            return false;
        }
        return Pattern.matches("\\d{4}-\\d{2}-\\d{2}\\s{1}\\d{2}:\\d{2}:\\d{2}", str);
    }

    /**
     * 把数字转换位英文字母
     * 
     * @param i
     * @return
     */
    public static String num2Charactor(int num) {
        if (num < 10 || num > 35) {
            return String.valueOf(num);
        }
        return String.valueOf((char) (55 + num));
    }

    /**
     * A-0;B-1;....;AA;AB
     * 
     * @param charactor
     * @return
     */
    public static int str2Num(String charactor) {
        if (charactor == null || charactor.equals("")) {
            return 0;
        }
        int rtInt = 0;
        String tmpStr = charactor.toUpperCase();
        for (int i = 0; i < tmpStr.length(); i++) {
            char c = tmpStr.charAt(tmpStr.length() - 1 - i);
            rtInt += ((int) (c - 65 + 1) * (int) Math.pow(26, i));
        }
        return rtInt - 1;
    }

    /**
     * 把字符串转化为Date
     * 
     * @param dateStr
     * @return
     */
    public static Date parseDate(String formatStr, String dateStr) throws ParseException {
        if (dateStr != null) {
            SimpleDateFormat format = new SimpleDateFormat(formatStr);
            return format.parse(dateStr);
        }
        return null;
    }

    /**
     * 把字符串转化为Date
     * 
     * @param dateStr
     * @return
     */
    public static Date parseDate(String dateStr) throws ParseException {
        if (dateStr == null || "".equals(dateStr)) {
            return null;
        }

        SimpleDateFormat format = null;
        if (Pattern.matches("\\d{4}-\\d{1,2}-\\d{1,2}", dateStr)) {
            format = new SimpleDateFormat("yyyy-MM-dd");
        } else if (Pattern.matches("\\d{4}\\d{2}\\d{2}", dateStr)) {
            format = new SimpleDateFormat("yyyyMMdd");
        } else if (Pattern.matches("\\d{4}年\\d{2}月\\d{2}日", dateStr)) {
            format = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        } else if (Pattern.matches("\\d{4}年\\d{1,2}月\\d{1,2}日", dateStr)) {
            format = new SimpleDateFormat("yyyy年M月d日", Locale.CHINA);
        } else if (Pattern.matches("\\d{1,2}\\w{3}\\d{4}", dateStr)) {
            format = new SimpleDateFormat("dMMMyyyy", Locale.ENGLISH);
        } else if (Pattern.matches("\\d{1,2}-\\w{3}-\\d{4}", dateStr)) {
            format = new SimpleDateFormat("d-MMM-yyyy", Locale.ENGLISH);
        } else if (dateStr.length() > 20) {
            format = dateFormatS;
        } else {
            format = dateFormat;
        }

        return format.parse(dateStr);
    }

    /**
     * 当前日期转化成java.sql.Date对象
     * 
     * @param dateStr
     * @return
     */
    public static java.sql.Timestamp parseTimeStamp() throws ParseException {
        return parseTimeStamp(null);
    }

    /**
     * 把字符串转化为java.sql.Date对象
     * 
     * @param longDate
     * @return
     */
    public static java.sql.Timestamp parseTimeStamp(long longDate) throws ParseException {
        Date utilDate = new Date(longDate);
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
        return sqlDate;
    }

    /**
     * 把字符串转化为java.sql.Date对象
     * 
     * @param dateStr
     * @return
     */
    public static java.sql.Timestamp parseTimeStamp(String dateStr) throws ParseException {
        Date utilDate = null;
        if (dateStr == null) {
            utilDate = new Date();
        } else {
            utilDate = SysTool.parseDate(dateStr);
        }
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
        return sqlDate;
    }

    /**
     * 当前日期转化成java.sql.Date对象
     * 
     * @param dateStr
     * @return
     */
    public static java.sql.Date parseSqlDate() throws ParseException {
        return parseSqlDate(null);
    }

    /**
     * 把字符串转化为java.sql.Date对象
     * 
     * @param dateStr
     * @return
     */
    public static java.sql.Date parseSqlDate(String dateStr) throws ParseException {
        Date utilDate = null;
        if (dateStr == null) {
            utilDate = new Date();
        } else {
            utilDate = SysTool.parseDate(dateStr);
        }
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

    /**
     * 解析数值
     * 
     * @param numStr
     * @return
     */
    public static double parseDouble(String numStr) throws ParseException {
        if (isNullorEmpty(numStr)) {
            return 0.00;
        }
        if (numStr.indexOf(",") > 0) {
            return numFormatG.parse(numStr).doubleValue();
        }
        return Double.parseDouble(numStr);
    }

    /**
     * 转换编码
     * 
     * @return
     */
    public static String transferCode(String srcStr, String srcCode, String desCode)
            throws UnsupportedEncodingException {
        if (srcStr == null) {
            return null;
        }
        return new String(srcStr.getBytes(srcCode), desCode);
    }

    /**
     * 把编码为ISO-8859-1的转换为GBK
     * 
     * @param srcStr
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String iso88591ToGbk(String srcStr) throws UnsupportedEncodingException {
        return transferCode(srcStr, "ISO-8859-1", "GBK");
    }

    /**
     * 把编码为ISO-8859-1的转换为UTF-8
     * 
     * @param srcStr
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String iso88591ToUTF8(String srcStr) throws UnsupportedEncodingException {
        return transferCode(srcStr, "ISO-8859-1", "UTF-8");
    }

    /**
     * 取得HTML形式的缩进字符串
     * 
     * @return
     */
    public static String getHtmlIdent(int identCnt) {
        return getIdentStr(identCnt, KeyConst.HTML_SPACE);
    }

    /**
     * 取得中文空格形式的缩进字符串
     * 
     * @return
     */
    public static String getChinaSpaceIdent(int identCnt) {
        return getIdentStr(identCnt, KeyConst.CHINA_SPACE);
    }

    /**
     * 判断金额是否相等，这里考虑舍入因素，并非严格数学意义上的相等
     * 
     * @param amt1
     *            金额1
     * @param amt2
     *            金额2
     * @param amtScale
     *            金额的小数位
     * @return
     */
    public static boolean isEqualAmt(double amt1, double amt2, int amtScale) {

        if (amt1 == amt2) {
            return true;
        }

        if (Math.abs(amt1 - amt2) < Math.pow(10, (0 - amtScale))) {
            return true;
        }
        return false;
    }

    /**
     * 取得中文空格的缩进
     * 
     * @param identCnt
     *            缩进层次数
     * 
     * @param identStr
     *            缩进字符串
     * 
     * @return
     */
    private static String getIdentStr(int identCnt, String identStr) {
        StringBuffer buff = new StringBuffer();

        for (int i = 0; i < identCnt; i++) {
            buff.append(identStr);
        }
        return buff.toString();
    }

    // read an input-stream into a String
    public static String loadStream(InputStream in) throws Exception {
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int readLen = 0;
            while ((readLen = in.read(buff)) > 0) {
                out.write(buff, 0, readLen);
            }
        } catch (Exception ex) {
            log.debug(ex.getMessage(), ex);
            throw ex;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception ex) {
            }
        }
        return out.toString();
    }

    /**
     * 执行命令
     * 
     * @param cmd
     */
    public static String exCmd(String cmd) {
        try {
            Runtime r = Runtime.getRuntime();
            Process process = r.exec(cmd);
            InputStream in = null;
            in = process.getInputStream();
            String outMsrg = loadStream(in);

            process.waitFor();
            process.destroy();

            return outMsrg;
        } catch (Exception ex) {
            log.debug(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * 避免取得计数法的String形式的双精度型字符串
     * 
     * @param bgd
     * @return
     */
    public static String decimal2DoubleStr(BigDecimal bgd) {
        return getFormatedStr(bgd.doubleValue(), WITHOUTGROUP);
    }

    /**
     * 带精度要求的四则运算-加法
     * 
     * @param opt1
     *            操作数1
     * @param opt2
     *            操作数2
     * @param scale
     *            小数位精度
     * 
     * @return
     */
    public static BigDecimal add(double opt1, double opt2, int scale) {
        BigDecimal bgd1 = new BigDecimal(opt1);
        BigDecimal bgd2 = new BigDecimal(opt2);

        return add(bgd1, bgd2, scale);
    }

    /**
     * 带精度要求的四则运算-加法
     * 
     * @param bgd1
     *            操作数1
     * @param opt2
     *            操作数2
     * @param scale
     *            小数位精度
     * 
     * @return
     */
    public static BigDecimal add(BigDecimal bgd1, double opt2, int scale) {
        BigDecimal bgd2 = new BigDecimal(opt2);

        return add(bgd1, bgd2, scale);
    }

    /**
     * 带精度要求的四则运算-加法
     * 
     * @param bgd1
     *            操作数1
     * @param opt2
     *            操作数2
     * @param scale
     *            小数位精度
     * 
     * @return
     */
    public static BigDecimal add(BigDecimal opt1, BigDecimal opt2, int scale) {
        if (opt1 == null) {
            opt1 = new BigDecimal(0.00);
        }
        if (opt2 == null) {
            opt2 = new BigDecimal(0.00);
        }
        opt1 = opt1.setScale(scale, BigDecimal.ROUND_HALF_UP);
        opt2 = opt2.setScale(scale, BigDecimal.ROUND_HALF_UP);

        return opt1.add(opt2);
    }

    /**
     * 带精度要求的四则运算-减法
     * 
     * @param opt1
     *            操作数1
     * @param opt2
     *            操作数2
     * @param scale
     *            小数位精度
     * 
     * @return
     */
    public static BigDecimal subtract(double opt1, double opt2, int scale) {
        BigDecimal bgd1 = new BigDecimal(opt1);
        BigDecimal bgd2 = new BigDecimal(opt2);

        return subtract(bgd1, bgd2, scale);
    }

    /**
     * 带精度要求的四则运算-减法
     * 
     * @param bgd1
     *            操作数1
     * @param opt2
     *            操作数2
     * @param scale
     *            小数位精度
     * 
     * @return
     */
    public static BigDecimal subtract(BigDecimal bgd1, double opt2, int scale) {
        BigDecimal bgd2 = new BigDecimal(opt2);

        return subtract(bgd1, bgd2, scale);
    }

    /**
     * 带精度要求的四则运算-减法
     * 
     * @param opt1
     *            操作数1
     * @param opt2
     *            操作数2
     * @param scale
     *            小数位精度
     * 
     * @return
     */
    public static BigDecimal subtract(BigDecimal opt1, BigDecimal opt2, int scale) {
        if (opt1 == null) {
            opt1 = new BigDecimal(0.00);
        }
        if (opt2 == null) {
            opt2 = new BigDecimal(0.00);
        }
        opt1 = opt1.setScale(scale, BigDecimal.ROUND_HALF_UP);
        opt2 = opt2.setScale(scale, BigDecimal.ROUND_HALF_UP);

        return opt1.subtract(opt2);
    }

    /**
     * 带精度要求的四则运算-乘法
     * 
     * @param opt1
     *            操作数1
     * @param opt2
     *            操作数2
     * @param scale
     *            小数位精度
     * 
     * @return
     */
    public static BigDecimal multiply(double opt1, double opt2, int scale) {
        BigDecimal bgd1 = new BigDecimal(opt1);
        BigDecimal bgd2 = new BigDecimal(opt2);

        return multiply(bgd1, bgd2, scale);
    }

    /**
     * 带精度要求的四则运算-乘法
     * 
     * @param bgd1
     *            操作数1
     * @param opt2
     *            操作数2
     * @param scale
     *            小数位精度
     * 
     * @return
     */
    public static BigDecimal multiply(BigDecimal bgd1, double opt2, int scale) {
        BigDecimal bgd2 = new BigDecimal(opt2);

        return multiply(bgd1, bgd2, scale);
    }

    /**
     * 带精度要求的四则运算-乘法
     * 
     * @param bgd1
     *            操作数1
     * @param opt2
     *            操作数2
     * @param scale
     *            小数位精度
     * 
     * @return
     */
    public static BigDecimal multiply(BigDecimal opt1, BigDecimal opt2, int scale) {
        if (opt1 == null) {
            opt1 = new BigDecimal(0.00);
        }
        if (opt2 == null) {
            opt2 = new BigDecimal(0.00);
        }
        opt1 = opt1.setScale(scale, BigDecimal.ROUND_HALF_UP);
        opt2 = opt2.setScale(scale, BigDecimal.ROUND_HALF_UP);

        return opt1.multiply(opt2);
    }

    /**
     * 带精度要求的四则运算-除法
     * 
     * @param opt1
     *            操作数1
     * @param opt2
     *            操作数2
     * @param scale
     *            小数位精度
     * 
     * @return
     */
    public static BigDecimal divide(double opt1, double opt2, int scale) {
        BigDecimal bgd1 = new BigDecimal(opt1);
        BigDecimal bgd2 = new BigDecimal(opt2);

        return divide(bgd1, bgd2, scale);
    }

    /**
     * 带精度要求的四则运算-除法
     * 
     * @param bgd1
     *            操作数1
     * @param opt2
     *            操作数2
     * @param scale
     *            小数位精度
     * 
     * @return
     */
    public static BigDecimal divide(BigDecimal bgd1, double opt2, int scale) {
        BigDecimal bgd2 = new BigDecimal(opt2);
        bgd2.setScale(scale, BigDecimal.ROUND_HALF_UP);

        return divide(bgd1, bgd2, scale);
    }

    /**
     * 带精度要求的四则运算-除法
     * 
     * @param bgd1
     *            操作数1
     * @param opt2
     *            操作数2
     * @param scale
     *            小数位精度
     * 
     * @return
     */
    public static BigDecimal divide(BigDecimal opt1, BigDecimal opt2, int scale) {
        if (opt1 == null) {
            opt1 = new BigDecimal(0.00);
        }
        if (opt2 == null) {
            opt2 = new BigDecimal(1.00);
        }
        opt1 = opt1.setScale(scale, BigDecimal.ROUND_HALF_UP);
        opt2 = opt2.setScale(scale, BigDecimal.ROUND_HALF_UP);

        return opt1.divide(opt2, scale * 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 取得双精度型字符串，解决科学计数法问题
     * 
     * @param bg
     * @return
     */
    public static String parseDoubleStr(BigDecimal bg, int format) {
        return SysTool.getFormatedStr(bg.doubleValue(), format);
    }

    /**
     * 解析双精度型字符串，解决科学计数法问题
     * 
     * @param bg
     * @return
     */
    public static double parseDouble(BigDecimal bg) {
        if (bg == null) {
            return 0.00;
        }
        return Double.parseDouble(parseDoubleStr(bg, WITHOUTGROUP));
    }

    /**
     * 用系统时间和随机数产生系统唯一的文件名
     * 
     * @return
     */
    public static String getRandomName() {
        String currTimeStr = SysTool.getCurDateTimeStr(SysTool.DATE_FORMAT_NOSPLIT);
        String randomStr = String.valueOf((int) Math.random() * 10000);
        return currTimeStr + randomStr;
    }

    /**
     * 把字符串转换为属性哈希表
     * 
     * @param propStr
     * @param rtMap
     */
    public static void str2Map(String propStr, Map rtMap) {
        String[] propArray = propStr.split("\\\r*\\\n{1}");
        for (int i = 0; i < propArray.length; i++) {
            String tmpStr = propArray[i].trim();
            if (tmpStr.length() < 1 || tmpStr.startsWith("#")) {
                continue;
            }
            int tmpIndex = tmpStr.indexOf("=");
            if (tmpIndex < 0) {
                continue;
            }
            String key = tmpStr.substring(0, tmpIndex).trim();
            String value = tmpStr.substring(tmpIndex + 1).trim();
            if (key.length() < 1 || value.length() < 1) {
                continue;
            }
            rtMap.put(key, value);
        }
    }

    /**
     * Copy 哈希表
     * 
     * @param fromMap
     * @param toMap
     */
    public static void copyMap(Map fromMap, Map toMap) {
        Iterator iKeys = fromMap.keySet().iterator();
        while (iKeys.hasNext()) {
            Object key = iKeys.next();
            Object value = fromMap.get(key);
            toMap.put(key, value);
        }
    }

    /**
     * 清理指定前缀的属性
     * 
     * @param srcMap
     * @param postFix
     */
    public static void clearMapPre(Map<String, String> srcMap, String preFix) {
        List<String> keyList = new ArrayList<String>();

        Iterator<String> iKeys = srcMap.keySet().iterator();
        while (iKeys.hasNext()) {
            String key = iKeys.next();
            if (key.startsWith(preFix)) {
                keyList.add(key);
            }
        }
        for (int i = 0; i < keyList.size(); i++) {
            srcMap.remove(keyList.get(i));
        }
    }

    /**
     * 清理指定后缀的属性
     * 
     * @param srcMap
     * @param postFix
     */
    public static void clearMapPost(Map<String, String> srcMap, String postFix) {
        List<String> keyList = new ArrayList<String>();

        Iterator<String> iKeys = srcMap.keySet().iterator();
        while (iKeys.hasNext()) {
            String key = iKeys.next();
            if (key.endsWith(postFix)) {
                keyList.add(key);
            }
        }
        for (int i = 0; i < keyList.size(); i++) {
            srcMap.remove(keyList.get(i));
        }
    }

    /**
     * URL编码
     */
    public static String encodeURL(String srcStr) throws Exception {
        if (srcStr == null) {
            return "";
        }
        return URLEncoder.encode(srcStr, SysConst.DEFAULT_CODE);
    }

    /**
     * URL编码
     */
    public static String decodeURL(String srcStr) throws Exception {
        if (srcStr == null) {
            return "";
        }
        return URLDecoder.decode(srcStr, SysConst.DEFAULT_CODE);
    }

    /**
     * 处理\ " '
     * 
     * @param srcStr
     * @return
     * @throws Exception
     */
    public static String encodeHtml(String srcStr) {
        if (srcStr == null) {
            return "";
        }
        return srcStr.replace("\\", "&#92;").replace("\"", "&#34;").replace("\'", "&#39;")
                .replace("\r\n", "&#10;&#13;").replace("\n", "&#13;").replace("\r", "&#10;");
    }

    /**
     * 处理\ " '
     * 
     * @param srcStr
     * @return
     * @throws Exception
     */
    public static String encodeQuote(String srcStr) {
        if (srcStr == null) {
            return "";
        }
        return srcStr.replace("\\", "\\\\").replace("\"", "\\\"").replace("\'", "\\\'");
    }

    /**
     * 处理\ " '
     * 
     * @param srcStr
     * @return
     * @throws Exception
     */
    public static String encodeSpecial(String srcStr) {
        if (srcStr == null) {
            return "";
        }
        return srcStr.replace("\\", "\\\\").replace("\"", "\\\"").replace("\'", "\\\'").replace("\r\n", "")
                .replace("\n", "").replace("\r", "");
    }

    /**
     * 处理' % _
     * 
     * @param srcStr
     * @return
     * @throws Exception
     */
    public static String encodeLike(String srcStr) {
        if (srcStr == null) {
            return "";
        }
        return srcStr.replace("\\", "\\\\").replace("'", "''").replace("%", "\\%").replace("_", "\\_");
    }

    /**
     * 获取Key前面部分相同的子哈希表
     * 
     * @param srcMap
     * @param prefix
     * @return
     */
    public static Map<String, String> startsWithMap(Map srcMap, String prefix) {
        Map<String, String> rtMap = new HashMap<String, String>();

        if (isNullorEmpty(prefix)) {
            return rtMap;
        }
        Iterator<String> iKeys = srcMap.keySet().iterator();
        while (iKeys.hasNext()) {
            String key = iKeys.next();
            if (key.startsWith(prefix)) {
                rtMap.put(key, srcMap.get(key).toString());
            }
        }
        return rtMap;
    }

    /**
     * 获取Key后面部分相同的子哈希表
     * 
     * @param srcMap
     * @param prefix
     * @return
     */
    public static Map endsWithMap(Map srcMap, String postfix) {
        Map rtMap = new HashMap<String, Object>();

        if (isNullorEmpty(postfix)) {
            return rtMap;
        }
        Iterator<String> iKeys = srcMap.keySet().iterator();
        while (iKeys.hasNext()) {
            String key = iKeys.next();
            if (key.endsWith(postfix)) {
                rtMap.put(key, srcMap.get(key));
            }
        }
        return rtMap;
    }

    /**
     * 去掉所有html标签
     * 
     * @param html
     * @return
     */
    public static String cutHtml(String html) {
        String result = "";
        result = html.replaceAll("(<[^/\\s][\\w]*)[\\s]*([^>]*)(>)", "$1$3").replaceAll("<[^>]*>", "");
        return result;
    }

    /**
     * 类型转换成Long
     * 
     * @param value
     * @return
     */
    public static Long cast2Long(Object value) throws ErrorMessage {
        if (value == null || value.toString().equals("")) {
            return new Long(0);
        }
        try {
            return (Long) value;
        } catch (Exception ex) {
            try {
                return new Long(((Integer) value));
            } catch (Exception ex2) {
                try {
                    int valueInt = (int) ((Double) value).doubleValue();
                    return Long.valueOf(valueInt);
                } catch (Exception ex3) {
                    throw new ErrorMessage("不能转换成长整型 >> " + value.toString() + " >> "
                            + value.getClass().getName());
                }
            }
        }
    }

    public static Date strToDate(String time) {
        Date ctime = null;
        try {
            int colonCount = signCount(time, ':');
            if (time == null) {
                return null;
            } else {
                time = time.replaceAll("\\+", " ");
            }
            SimpleDateFormat formatter;
            int tempPos = time.indexOf("AD");
            time = time.trim();
            formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
            if (tempPos > -1) {
                time = time.substring(0, tempPos) + "公元" + time.substring(tempPos + "AD".length());// china
                formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
            }
            tempPos = time.indexOf("-");
            if (tempPos > -1 && (time.indexOf(" ") < 0) && time.length() > 10) {
                formatter = new SimpleDateFormat("yyyyMMddHHmmssZ");
            } else if (time.indexOf("/") > -1) {
                if (time.indexOf(" ") > -1) {
                    if (colonCount == 1) {
                        formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                    } else if (colonCount == 2) {
                        formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    }
                } else {
                    formatter = new SimpleDateFormat("yyyy/MM/dd");
                }
            } else if (time.indexOf("-") > -1) {
                if (time.indexOf(" ") > -1) {
                    if (colonCount == 1) {
                        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    } else if (colonCount == 2) {
                        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    }
                } else {
                    formatter = new SimpleDateFormat("yyyy-MM-dd");
                }
            } else if ((time.indexOf("/") > -1) && (time.indexOf("am") > -1) || (time.indexOf("pm") > -1)) {
                formatter = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
            } else if ((time.indexOf("-") > -1) && (time.indexOf("am") > -1) || (time.indexOf("pm") > -1)) {
                formatter = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
            }
            ParsePosition pos = new ParsePosition(0);
            ctime = formatter.parse(time, pos);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ctime;
    }

    public static int signCount(String str, char sign) {
        int count = 0;
        if (str != null) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == sign) {
                    count++;
                }
            }
        }
        return count;
    }

    public static String getDateTimeStr(Date date, String format) {
        if (date != null) {
            return new SimpleDateFormat(format).format(date);
        }
        return null;
    }

    public static void closePrintWriter(PrintWriter writer) {
        try {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        } catch (Exception e) {
        }
    }

    /**
     * 数据操作，数组子元素结合指定字符进行拼接 数组中的空值元素将以空字符代替拼接
     * 
     * @param array
     * @param joinStr
     * @return
     */
    public static String join(Object[] array, String joinStr) {
        String joinResult = null;
        if (joinStr == null) {
            joinStr = "";
        }
        if (array.length > 0) {
            joinResult = "";
            for (int i = 0; i < array.length; i++) {
                joinResult += (array[i] == null ? "" : array[i].toString()) + joinStr;
            }
            joinResult = joinResult.substring(0, joinResult.length() - joinStr.length());
        }
        return joinResult;
    }

    public static void closeInputStream(InputStream inputStream) {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Exception e) {
        }
    }

    public static void closeOutputStream(OutputStream outputStream) {
        try {
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
        }
    }

    // 获取SESSIONID
    public static void getSessionId(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(sessionId);
        response.getWriter().flush();
    }

    /**
     * 获取当前项目web-inf绝对路径，如：D:project/tfdsys/webroot/tfd/web-inf/,路径后已带上了“/”
     * 
     * @return
     */
    public static String getProjectWebInfAbsolutePath() {
        String path = SysTool.class.getClassLoader().getResource(File.separator).getPath() + "#";
        String[] pathStrs = path.split("/");
        StringBuffer webinfPath = new StringBuffer();
        for (int i = 0; i < pathStrs.length - 2; i++) {
            webinfPath.append(pathStrs[i] + "/");
        }
        return webinfPath.toString();
    }

    /**
     * 获取提交表单的第一个文件流，表单类型为multipart/form-data
     * 
     * @param request
     * @return
     * @throws Exception
     */
    public static InputStream getFileInputStreamOfRequestForm(HttpServletRequest request) throws Exception {
        InputStream is = null;
        DiskFileItemFactory disk = new DiskFileItemFactory();
        disk.setSizeThreshold(1024 * 5);// 设置上传缓冲区容量为5k

        String tempPath = request.getSession().getServletContext().getRealPath(File.separator) + "temp";
        File tempFile = new File(tempPath);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
        disk.setRepository(tempFile);

        ServletFileUpload sfu = new ServletFileUpload(disk);
        sfu.setHeaderEncoding("utf-8");

        List items = sfu.parseRequest(request);

        if (items != null && items.size() > 0) {
            for (Object object : items) {
                FileItem item = (FileItem) object;
                // 文件字段
                if (!item.isFormField()) {
                    is = item.getInputStream();
                    break;
                }
            }
        }
        return is;
    }

    /**
     * @author fzd 判断 当前请求是否是手机设备发起的
     * @param userAgent
     * @return
     */
    public static boolean isMobilePhoneDevice(String userAgent) {
        if (userAgent == null)
            return false;
        userAgent = userAgent.toLowerCase();

        String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i" + "|windows (phone|ce)|blackberry"
                + "|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp"
                + "|laystation portable)|nokia|fennec|htc[-_]"
                + "|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";

        return Pattern.compile(phoneReg).matcher(userAgent).find();
    }

    /**
     * @author fzd 判断 当前请求是否是平板设备发起的
     * @param userAgent
     * @return
     */
    public static boolean isTabletDevice(String userAgent) {
        if (userAgent == null)
            return false;
        userAgent = userAgent.toLowerCase();

        String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";

        Pattern tablePat = Pattern.compile(tableReg);

        return tablePat.matcher(userAgent).find();
    }

}
