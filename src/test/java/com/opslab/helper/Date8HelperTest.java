package com.opslab.helper;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class Date8HelperTest {

    @Test
    private void test(){
         String curDate = "2019-04-10"; // 指定日期
         System.out.println(Date8Helper.getLastDayOfMonth(curDate, true));
         System.out.println(Date8Helper.getLastDayOfMonth(curDate, false));
         System.out.println(Date8Helper.getLastDayOfYear(curDate, true));
         System.out.println(Date8Helper.getLastDayOfYear(curDate, false));
         System.out.println("===================");
         String startDate = "2019-02-28", endDate = "2019-03-05", period = Date8Helper.DAY;
         System.out.println(Date8Helper.getPieDateRange(startDate, endDate, period));
         System.out.println("===================");
         System.out.println(Date8Helper.getNextWeekDate("2019-02-28", 1, false));
         System.out.println(Date8Helper.getPieDateRange("2019-12-28", "2020-03-01", Date8Helper.DAY));
         System.out.println("===================");
         System.out.println(Date8Helper.getFirstOrLastWeekDate("2019-04-02", 0, false));
         System.out.println(Date8Helper.getPreWeekDate("2019-04-02", 2, false));
         System.out.println(Date8Helper.getNextWeekDate("2019-04-02", 2, false));
         System.out.println("===================");
         System.out.println("当前时间戳：" + Instant.now());
         System.out.println("当前时间：" + LocalDateTime.now());
         System.out.println("===================");
         System.out.println(Date8Helper.peridCount("2019-01-30", "2019-03-31", Date8Helper.MONTH));
         System.out.println(Date8Helper.isLeapYear("2019-03-31"));
         System.out.println(LocalDate.now().isLeapYear());
         System.out.println("===================");
         System.out.println(Date8Helper.getAfterOrPreDate("2019-03-31", Date8Helper.WEEK, -1));
         System.out.println(Date8Helper.getAfterOrPreDayDate(-5));
         System.out.println(Date8Helper.getAfterOrPreDayDate(-3));
         System.out.println(Date8Helper.getAfterOrPreDayDate(6));
         System.out.println(Date8Helper.getAfterOrPreYearDate(6));
         System.out.println(Date8Helper.getAfterOrPreWeekDate(1));
         System.out.println("===================");
         LocalDate date0 = LocalDate.of(2019, Month.OCTOBER, 31);
         LocalDate date = LocalDate.of(2019, 3, 31);
         System.out.println(date0.equals(LocalDate.now()));
         System.out.println(date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth());
         System.out.println(Date8Helper.getNowTime_EN());
         System.out.println("===================");
         System.out.println(Date8Helper.getNowDate_EN());
         System.out.println(Date8Helper.getNowDate_CN());
         System.out.println(Date8Helper.getTime("HH:mm:ss"));
         System.out.println(Date8Helper.getNowTime_EN());
         System.out.println(Date8Helper.getNowTime_CN());
         System.out.println(Date8Helper.getNodeTime("week"));
         System.out.println(Date8Helper.transformWeekEN2Num(null));
         System.out.println("===================");
    }

}