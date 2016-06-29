package com.bms.system.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lxh
 * @version expweb 1.0.0 2013年11月4日
 * @since expweb 1.0.0
 * @description 日期和字符串转换工具
 */
public class DateUtil
{
    /**
     * 调测日志记录器。
     */
    private static final Logger log = Logger.getLogger(DateUtil.class);

    /**
     * Date转字符串
     * @param date 日期
     * @param format 转换格式
     * @return 字符串日期
     */
    public static String dateToStr(Date date, String format)
    {
        if (date == null)
        {
            return "";
        }
        else
        {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }
    }

    /**
     * 字符串转日期
     * @param date  字符串格式日期
     * @param format 转换格式
     * @return 日期类型 Date
     */
    public static Date strToDate(String date, String format)
    {
        Date time = null;
        if (!StringUtils.isEmpty(date))
        {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try
            {
                time = sdf.parse(date);
            }
            catch (ParseException e)
            {
                log.error("", e);
            }
        }

        return time;
    }

    /**
     * 获取月的最后一天日期字符串
     * @param date 时间
     * @param format 格式
     * @return 月的最后一天的日期字符串
     */
    public static String getLastDayOfMonth(Date date, String format)
    {
        if (null == date)
        {
            return "";
        }
        else
        {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, 0);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            return sdf.format(calendar.getTime());
        }
    }
}
