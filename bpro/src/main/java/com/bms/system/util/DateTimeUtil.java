package com.bms.system.util;

import java.util.Calendar;

public class DateTimeUtil
{

    /**
     * 
     * @param falg
     *            beg:开始时间（00:00:00）<br>
     *            end:结束时间（23:59:59）
     * @return
     */
    public static String getBegTime(String falg)
    {
        String temp = null;
        Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        falg = falg == null ? falg : (falg.length() > 0 ? (falg = falg
                .toLowerCase()) : falg);
        if ("end".equals(falg))
        {
            temp = year + "-" + month + "-" + date + " 23:59:59";
        }
        else if ("beg".equals(falg))
        {
            temp = year + "-" + month + "-" + date + " 00:00:00";
        }
        return temp;
    }
    
    public static String getCurrentTime(){
    	Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改
    	
    	int year = c.get(Calendar.YEAR);
	    int month = c.get(Calendar.MONTH) + 1;
	    
    	return year+(String.valueOf((month<10?("0"+month):month)));
    }

    public static String getCurrentDateTime(){
    	return String.valueOf(System.currentTimeMillis());   
    }
    
    public static void main(String[] args)
    {
    	System.out.println(getCurrentDateTime());
        // String formart = null;
        // formart = formart==null?return null:formart.s
        // .length()>0?"true":"false";
       // System.out.println(getBegTime("beG"));
    }

}
