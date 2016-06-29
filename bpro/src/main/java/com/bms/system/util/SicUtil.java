package com.bms.system.util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.FilterConfig;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

/**
 * @author lvxh
 * @version sic 1.0.0 2014年9月24日
 * @since sic 1.0.0
 */
public class SicUtil
{
    /**
     * 调测日志记录器。
     */
    private static final Logger log = Logger.getLogger(SicUtil.class);

    private static final Lock LOCK = new ReentrantLock();

    private static ApplicationContext ctx;


    public static final ExecutorService pool = Executors.newCachedThreadPool();


    public static Lock getLock()
    {
        return LOCK;
    }

    /**
     * 关闭此流并释放与此流关联的所有系统资源。
     *
     * @param obj 是可以关闭的数据源或目标
     */
    public static void close(AutoCloseable obj)
    {
        if (obj != null)
        {
            try
            {
                obj.close();
            }
            catch (Exception e)
            {

                log.error("Failed to close", e);
            }
        }

    }

    /**
     * 时间类型转换为字符串类型
     *
     * @param date   需要转换的时间对象
     * @param format 格式
     * @return 格式化之后的字符串
     */
    public static String dateFormat(Date date, String format)
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
     * 时间字符串转换为时间类型
     *
     * @param date   需要转换的时间字符串
     * @param format 格式
     * @return 格式化之后的时间对象
     */
    public static Date dateFormat(String date, String format)
    {
        Date time = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try
        {
            time = sdf.parse(date);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }

        return time;
    }

    @SuppressWarnings("unused")
    private static void initCTX(FilterConfig config)
    {
        ctx = WebApplicationContextUtils.getWebApplicationContext(config
                .getServletContext());
    }


    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name)
    {
        return (T) ctx.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz)
    {
        return ctx.getBean(clazz);
    }

    /**
     * 效应字段是否有值
     *
     * @param col 效应字段
     * @return 是否有值
     */
    public static boolean validate(String col)
    {
        boolean temp = false;
        if (null != col && !col.isEmpty())
        {
            temp = true;
        }
        return temp;
    }

    /**
     * 对象转换JSON串
     *
     * @param obj 对象
     * @return JSON对象
     */
    public static String ObjectToJson(Object obj)
    {
        Gson gson = new Gson();
        if (null != obj)
        {
            return gson.toJson(obj);
        }
        return null;
    }

    /**
     * SH256加密
     *
     * @param str 源
     * @return 加密后的字符串
     */
    public static String getSHA256(String str)
    {
        char[] temp = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F'};
        try
        {
            byte[] digest = MessageDigest.getInstance("SHA-256").digest(
                    str.getBytes());

            StringBuilder builder = new StringBuilder();
            for (byte b : digest)
            {
                /*
                 * int t = (b >> 4) & 0xf; builder.append((char)(t > 9 ? t + 'A'
				 * - 10 : t + '0')); t = b & 0xf; builder.append((char)(t > 9 ?
				 * t + 'A' - 10 : t + '0'));
				 */
                builder.append(temp[(b >> 4) & 0xf]).append(temp[b & 0xf]);
            }
            return builder.toString();
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
