package com.ruanmeng.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeHelper {

    private int weeks = 0; //用来全局控制 上一周，本周，下一周的周数变化
    private int MaxDate; //一月最大天数
    private int MaxYear; //一年最大天数

	/*
	 * 判断是否是闰年: isLeapYear()
	 * 得到6个月后的日期: getAfterMonth(6)
	 * 获取当天日期: getNowTime("yyyy-MM-dd")
	 * 获取本周一日期: getMondayOFWeek()
	 * 获取本周日的日期~: getCurrentWeekday()
	 * 获取上周一日期: getPreviousWeekday()
	 * 获取上周日日期: getPreviousWeekSunday()
	 * 获取下周一日期: getNextMonday()
	 * 获取下周日日期: getNextSunday()
	 * 获得相应周的周六: getNowTime("yyyy-MM-dd")
	 * 获取本月第一天日期: getFirstDayOfMonth()
	 * 获取本月最后一天日期: getDefaultDay()
	 * 获取上月第一天日期: getPreviousMonthFirst()
	 * 获取上月最后一天的日期: getPreviousMonthEnd()
	 * 获取下月第一天日期: getNextMonthFirst()
	 * 获取下月最后一天日期: getNextMonthEnd()
	 * 获取本年的第一天日期: getCurrentYearFirst()
	 * 获取本年最后一天日期: getCurrentYearEnd()
	 * 获取去年的第一天日期: getPreviousYearFirst()
	 * 获取去年的最后一天日期: getPreviousYearEnd()
	 * 获取明年第一天日期: getNextYearFirst()
	 * 获取明年最后一天日期: getNextYearEnd()
	 * 获取本季度第一天到最后一天: getThisSeasonTime(11)
	 * 获取两个日期之间间隔天数: getTwoDay("2008-12-1","2008-9-29")
	 */

    public static TimeHelper getInstance() {
        return new TimeHelper();
    }

    /**
     * 判断是否润年
     *
     * @param ddate
     * @return
     */
    public boolean isLeapYear(String ddate) {

        /**
         * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
         * 3.能被4整除同时能被100整除则不是闰年
         */
        Date d = strToDate(ddate);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(d);
        int year = gc.get(Calendar.YEAR);
        if ((year % 400) == 0)
            return true;
        else if ((year % 4) == 0) {
            if ((year % 100) == 0)
                return false;
            else
                return true;
        } else
            return false;
    }

    /**
     * 获取现在时间
     *
     * @return
     */
    public Date getNow() {
        Date currentTime = new Date();
        return currentTime;
    }

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return返回短时间格式 yyyy-MM-dd
     */
    public Date getNowDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间  小时:分;秒 HH:mm:ss
     *
     * @return
     */
    public String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 根据用户传入的毫秒值，返回指定的时间格式
     */
    public String getFormatedDateTime(String pattern, long dateTime) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(new Date(dateTime));
    }

    /**
     * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
     *
     * @param sformat
     *            yyyyMMddhhmmss
     * @return
     */
    public String getUserDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public String dateToStrLong(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @return
     */
    public String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     */
    public Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
     */
    public String getTwoHour(String st1, String st2) {
        String[] kk = null;
        String[] jj = null;
        kk = st1.split(":");
        jj = st2.split(":");
        if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
            return "0";
        else {
            double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1]) / 60;
            double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1]) / 60;
            if ((y - u) > 0)
                return y - u + "";
            else
                return "0";
        }
    }

    /**
     * 时间前推或后推分钟,其中JJ表示分钟.
     */
    public String getPreTime(String sj1, String jj) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mydate1 = "";
        try {
            Date date1 = format.parse(sj1);
            long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
            date1.setTime(Time * 1000);
            mydate1 = format.format(date1);
        } catch (Exception e) {
        }
        return mydate1;
    }

    /**
     * 根据用户传入的时间和格式，返回指定时间的毫秒值
     *
     * @param @param format
     * @param @param nowtime
     * @return long    返回类型
     */
    public long stringToLong(String format, String nowtime) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        long time = 0;
        try {
            Date date = sdf.parse(nowtime);
            time = date.getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间 yyyy-MM-dd,delay为前移或后延的天数
     */
    public String getNextDay(String nowdate, String delay) {
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String mdate = "";
            Date d = strToDate(nowdate);
            long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = format.format(d);
            return mdate;
        }catch(Exception e){
            return "";
        }
    }

    /**
     * 得到指定月后（前）的日期 参数传负数即可
     */
    public String getAfterMonth(int month) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try{
            date = sdf.parse("2009-11-04");//初始日期
        }catch(Exception e){
            e.printStackTrace();
        }
        c.setTime(date);//设置日历时间
        c.add(Calendar.MONTH,month);//在日历的月份上增加6个月
        String strDate = sdf.format(c.getTime());//的到你想要得6个月后的日期
        return strDate;
    }

    /**
     * 得到二个日期间的间隔天数
     */
    public String getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            Date date = myFormatter.parse(sj1);
            Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }

    /**
     * 根据一个日期，返回是星期几的字符串
     */
    public String getWeek(String sdate) {
        // 再转换为时间
        Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour 中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    /**
     * 两个时间之间的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public long getDays(String date1, String date2) {
        if (date1 == null || date1.equals(""))
            return 0;
        if (date2 == null || date2.equals(""))
            return 0;
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        Date mydate = null;
        try {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
        } catch (Exception e) {
        }
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    // 计算当月最后一天,返回字符串
    public String getDefaultDay(){
        String str = "";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE,1);//设为当前月的1 号
        lastDate.add(Calendar.MONTH,1);//加一个月，变为下月的1 号
        lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天
        str=sdf.format(lastDate.getTime());
        return str;
    }

    // 上月第一天
    public String getPreviousMonthFirst(){
        String str = "";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE,1);//设为当前月的1 号
        lastDate.add(Calendar.MONTH,-1);//减一个月，变为下月的1 号
        //lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天
        str=sdf.format(lastDate.getTime());
        return str;
    }

    //获取当月第一天
    public String getFirstDayOfMonth(){
        String str = "";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE,1);//设为当前月的1 号
        str=sdf.format(lastDate.getTime());
        return str;
    }

    // 获得本周星期日的日期
    public String getCurrentWeekday() {
        weeks = 0;
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus+6);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    //获取当天时间
    public String getNowTime(String dateformat){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);//可以方便地修改日期格式
        String hehe = dateFormat.format(now);
        return hehe;
    }

    // 获得当前日期与本周日相差的天数
    private int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK)-1; //因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }

    //获得本周一的日期
    public String getMondayOFWeek(){
        weeks = 0;
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    //获得相应周的周六的日期
    public String getSaturday() {
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    // 获得上周星期日的日期
    public String getPreviousWeekSunday() {
        weeks=0;
        weeks--;
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus+weeks);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    // 获得上周星期一的日期
    public String getPreviousWeekday() {
        weeks--;
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    // 获得几周前（后）星期一的日期
    public String getMonday(int weeks) {
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    // 获得几周前（后）星期五的日期
    public String getFriday(int weeks) {
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 5);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    // 获得几周前（后）星期五的日期
    public String getDay(int weeks, int day) {
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + day);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    // 获得下周星期一的日期
    public String getNextMonday() {
        weeks++;
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    // 获得下周星期日的日期
    public String getNextSunday() {
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7+6);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    // 获得当前日期与本周日相差的天数
    public int getMonthPlus(){
        Calendar cd = Calendar.getInstance();
        int monthOfNumber = cd.get(Calendar.DAY_OF_MONTH);
        cd.set(Calendar.DATE, 1);//把日期设置为当月第一天
        cd.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        MaxDate=cd.get(Calendar.DATE);
        if(monthOfNumber == 1){
            return -MaxDate;
        }else{
            return 1-monthOfNumber;
        }
    }

    //获得上月最后一天的日期
    public String getPreviousMonthEnd(){
        String str = "";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.add(Calendar.MONTH,-1);//减一个月
        lastDate.set(Calendar.DATE, 1);//把日期设置为当月第一天
        lastDate.roll(Calendar.DATE, -1);//日期回滚一天，也就是本月最后一天
        str=sdf.format(lastDate.getTime());
        return str;
    }

    //获得下个月第一天的日期
    public String getNextMonthFirst(){
        String str = "";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.add(Calendar.MONTH,1);//减一个月
        lastDate.set(Calendar.DATE, 1);//把日期设置为当月第一天
        str=sdf.format(lastDate.getTime());
        return str;
    }

    //获得下个月最后一天的日期
    public String getNextMonthEnd(){
        String str = "";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.add(Calendar.MONTH,1);//加一个月
        lastDate.set(Calendar.DATE, 1);//把日期设置为当月第一天
        lastDate.roll(Calendar.DATE, -1);//日期回滚一天，也就是本月最后一天
        str=sdf.format(lastDate.getTime());
        return str;
    }

    //获得明年最后一天的日期
    public String getNextYearEnd(){
        String str = "";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.add(Calendar.YEAR,1);//加一个年
        lastDate.set(Calendar.DAY_OF_YEAR, 1);
        lastDate.roll(Calendar.DAY_OF_YEAR, -1);
        str=sdf.format(lastDate.getTime());
        return str;
    }

    //获得明年第一天的日期
    public String getNextYearFirst(){
        String str = "";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.add(Calendar.YEAR,1);//加一个年
        lastDate.set(Calendar.DAY_OF_YEAR, 1);
        str=sdf.format(lastDate.getTime());
        return str;
    }

    //获得本年有多少天
    public int getMaxYear(){
        Calendar cd = Calendar.getInstance();
        cd.set(Calendar.DAY_OF_YEAR,1);//把日期设为当年第一天
        cd.roll(Calendar.DAY_OF_YEAR,-1);//把日期回滚一天。
        int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
        return MaxYear;
    }

    public int getYearPlus(){
        Calendar cd = Calendar.getInstance();
        int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);//获得当天是一年中的第几天
        cd.set(Calendar.DAY_OF_YEAR,1);//把日期设为当年第一天
        cd.roll(Calendar.DAY_OF_YEAR,-1);//把日期回滚一天。
        int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
        if(yearOfNumber == 1){
            return -MaxYear;
        }else{
            return 1-yearOfNumber;
        }
    }

    //获得本年第一天的日期
    public String getCurrentYearFirst(){
        int yearPlus = this.getYearPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE,yearPlus);
        Date yearDay = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preYearDay = df.format(yearDay);
        return preYearDay;
    }

    //获得本年最后一天的日期 *
    public String getCurrentYearEnd(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");//可以方便地修改日期格式
        String years = dateFormat.format(date);
        return years+"-12-31";
    }

    //获得上年第一天的日期 *
    public String getPreviousYearFirst(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");//可以方便地修改日期格式
        String years = dateFormat.format(date); int years_value = Integer.parseInt
                (years);
        years_value--;
        return years_value+"-1-1";
    }

    //获得上年最后一天的日期
    public String getPreviousYearEnd(){
        weeks--;
        int yearPlus = this.getYearPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE,yearPlus+MaxYear*weeks+(MaxYear-1));
        Date yearDay = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preYearDay = df.format(yearDay);
        getThisSeasonTime(11);
        return preYearDay;
    }

    //获得本季度
    public String getThisSeasonTime(int month){
        int array[][] = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
        int season = 1;
        if(month>=1&&month<=3){
            season = 1;
        }
        if(month>=4&&month<=6){
            season = 2;
        }
        if(month>=7&&month<=9){
            season = 3;
        }
        if(month>=10&&month<=12){
            season = 4;
        }
        int start_month = array[season-1][0];
        int end_month = array[season-1][2];
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");//可以方便地修改日期格式
        String years = dateFormat.format(date);
        int years_value = Integer.parseInt(years);
        int start_days =1;//years+"-"+String.valueOf(start_month)+"-";//getLastDayOfMonth(years_value,start_month);
        int end_days = getLastDayOfMonth(years_value,end_month);
        String seasonDate =
                years_value+"-"+start_month+"-"+start_days+";"+years_value+"-"+end_month+"-"+end_days;
        return seasonDate;
    }

    /**
     * 获取某年某月的最后一天
     * @param year 年
     * @param month 月
     * @return 最后一天
     */
    private int getLastDayOfMonth(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
                || month == 10 || month == 12) {
            return 31;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        if (month == 2) {
            if (isLeapYear(year)) {
                return 29;
            } else {
                return 28;
            }
        }
        return 0;
    }

    /**
     * 是否闰年
     * @param year 年
     * @return
     */
    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
