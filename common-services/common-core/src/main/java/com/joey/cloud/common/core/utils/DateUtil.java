package com.joey.cloud.common.core.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间处理工具类
 * @author joey
 */
public class DateUtil {
    public static String format1="yyyy-MM-dd";
    public static String format2="yyyy-MM-dd HH:mm:ss";

    public static String date2Str(Date date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date str2Date(String str,String format)  {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(str);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return date;

    }

}
