package com.opslab.helper;


import com.opslab.helper.DateHelper;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DateHeplerTest {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Before
    public void setUp() throws Exception {

    }

    @org.junit.Test
    public void testGetDateTime() throws Exception {
        System.out.println(DateHelper.currentDateTime());
    }

    @org.junit.Test
    public void testGetDate() throws Exception {
        System.out.println(DateHelper.currentDate());
    }

    @org.junit.Test
    public void testGetTime() throws Exception {
        System.out.println(DateHelper.currentTime());
    }


    @Test
    public void testGetDateTime1() throws Exception {

    }

    @Test
    public void testGetDate1() throws Exception {

    }

    @Test
    public void testGetTime1() throws Exception {

    }

    @Test
    public void testYear() throws Exception {

    }

    @Test
    public void testYear1() throws Exception {

    }

    @Test
    public void testMonth() throws Exception {

    }

    @Test
    public void testMonth1() throws Exception {

    }

    @Test
    public void testDay() throws Exception {

    }

    @Test
    public void testDay1() throws Exception {

    }

    @Test
    public void testHour() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        Date date = DateHelper.hour(DateHelper.dateTime(str1), -0.5F);
        String rs = DateHelper.dateTime(date);
        assertEquals("转换有误", "2015-02-23 18:24:00", rs);
    }

    @Test
    public void testHour1() throws Exception {
        Date date = DateHelper.hour(-0.5F);
        assertTrue("计算错误", 30 == DateHelper.subtractMinute(date, new Date()));
    }

    @Test
    public void testMinute() throws Exception {

    }

    @Test
    public void testMinute1() throws Exception {

    }

    @Test
    public void testIsDate() throws Exception {

    }


    @Test
    public void testStringToDate1() throws Exception {

    }

    @Test
    public void testSubtract() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2015-02-23 18:55:22";
        long rs = DateHelper.subtract(str1, str2);
        System.out.println(rs);
        assertEquals("计算有误", 82L, rs);
    }

    @Test
    public void testSubtract1() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2015-02-23 18:55:22";
        long rs = DateHelper.subtract(sdf.parse(str1), sdf.parse(str2));
        assertEquals("计算有误", 82L, rs);
    }

    @Test
    public void testSubtractMinute() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2015-02-23 18:55:22";
        int rs = DateHelper.subtractMinute(str1, str2);
        assertEquals("计算有误", 1, rs);
    }

    @Test
    public void testSubtractMinute1() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2015-02-23 18:55:22";
        int rs = DateHelper.subtractMinute(sdf.parse(str1), sdf.parse(str2));
        assertEquals("计算有误", 1, rs);
    }

    @Test
    public void testSubtractHour() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2015-02-24 21:55:22";
        int rs = DateHelper.subtractHour(str1, str2);
        assertEquals("计算有误", 27, rs);
    }

    @Test
    public void testSubtractHour1() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2015-02-23 21:55:22";
        int rs = DateHelper.subtractHour(sdf.parse(str1), sdf.parse(str2));
        assertEquals("计算有误", 3, rs);
    }

    @Test
    public void testSubtractDay() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2015-03-26 21:55:22";
        int rs = DateHelper.subtractDay(str1, str2);
        assertEquals("计算有误", 31, rs);
    }

    @Test
    public void testSubtractDay1() throws Exception {
        String str1 = "2015-03-23 18:54:00";
        String str2 = "2015-03-25 21:55:22";
        int rs = DateHelper.subtractDay(str1, str2);
        assertEquals("计算有误", 2, rs);
    }

    @Test
    public void testSubtractMonth() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2016-08-25 21:55:22";
        int rs = DateHelper.subtractMonth(str1, str2);
        assertEquals("计算有误", 18, rs);
    }

    @Test
    public void testSubtractMonth1() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2016-08-25 21:55:22";
        int rs = DateHelper.subtractMonth(sdf.parse(str1), sdf.parse(str2));
        assertEquals("计算有误", 18, rs);
    }

    @Test
    public void testSubtractYear() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2016-08-25 21:55:22";
        int rs = DateHelper.subtractYear(sdf.parse(str1), sdf.parse(str2));
        assertEquals("计算有误", 1, rs);
    }

    @Test
    public void testSubtractYear1() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2016-08-25 21:55:22";
        int rs = DateHelper.subtractYear(str1, str2);
        assertEquals("计算有误", 1, rs);
    }

    @Test
    public void testSubtractTime() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2015-02-23 19:59:22";
        String rs = DateHelper.subtractTime(str1, str2);
        assertEquals("计算有误", "1:5:22", rs);
    }

    @Test
    public void testSubtractDate() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2015-02-25 19:59:22";
        String rs = DateHelper.subtractDate(str1, str2);
        assertEquals("计算有误", "2-1:5:22", rs);
    }

    @Test
    public void testSubDay() {
        System.out.println(DateHelper.subDay("2015-01-05 13:00:00", "2015-01-05 13:00:00"));
        System.out.println(DateHelper.subDay("2015-01-05 13:00:00", "2015-01-06 10:00:00"));
        System.out.println(DateHelper.subDay("2015-01-05 13:00:00", "2015-03-05 13:00:00"));
        System.out.println(DateHelper.subDay("2015-01-05 13:00:00", "2015-01-07 13:00:00"));
        System.out.println(DateHelper.subDay("2015-01-05 13:00:00", "2015-01-08 13:00:00"));
    }

    @Test
    public void testSubtimeBurst() throws ParseException {
        assertEquals("计算有误", DateHelper.subtimeBurst("2015-06-24 08:00:00",
                "2015-06-23 20:24:00", "08:00-21:00"), -2160);
        assertEquals("计算有误", DateHelper.subtimeBurst("2015-01-05 08:00:00",
                "2015-01-05 08:00:30", "08:00-21:00"), 30);

        assertEquals("计算有误", DateHelper.subtimeBurst("2015-01-05 20:59:01",
                "2015-01-05 21:00:00", "08:00-21:00"), 59);

        assertEquals("计算有误", DateHelper.subtimeBurst("2015-01-05 21:00:00",
                "2015-01-05 22:00:00", "08:00-21:00"), 0);

        assertEquals("计算有误", DateHelper.subtimeBurst("2015-01-05 20:59:02",
                "2015-01-06 21:00:00", "08:00-21:00"), 58L + (21 - 8) * 60 * 60);

        assertEquals("计算有误", DateHelper.subtimeBurst("2015-01-05 20:59:02",
                "2015-01-07 08:00:57", "08:00-21:00"), (21 - 8) * 3600 + 115L);
        assertEquals("计算有误", DateHelper.subtimeBurst("2016-03-08 14:37:03",
                "2016-03-09 09:37:03", "08:30-17:30"), 4 * 60 * 60);
    }

    @Test
    public void testCalculate23() {
        System.out.println(DateHelper.dateTime(DateHelper.calculate("2016-03-08 14:38:23",
                2 * 3600, "08:30-17:30")));
    }

    @Test
    public void testCalculate() throws ParseException {
        assertEquals("计算有误", DateHelper.dateTime(DateHelper.calculate("2015-01-29 20:59:50",
                9, "08:00-21:00")), "2015-01-29 20:59:59");
        assertEquals("计算有误", DateHelper.dateTime(DateHelper.calculate("2015-01-29 20:59:50",
                11, "08:00-21:00")), "2015-01-30 08:00:01");
        assertEquals("计算有误", DateHelper.dateTime(DateHelper.calculate("2015-01-29 20:59:50",
                (3600 * 13 + 1), "08:00-21:00")), "2015-01-30 20:59:51");
        assertEquals("计算有误", DateHelper.dateTime(DateHelper.calculate("2015-01-29 20:59:50",
                (3600 * 13 * 3 + 1), "08:00-21:00")), "2015-02-01 20:59:51");
        assertEquals("计算有误", DateHelper.dateTime(DateHelper.calculate("2015-01-29 20:59:50",
                (3600 * 13 * 3 + 11), "08:00-21:00")), "2015-02-02 08:00:01");

        assertEquals("计算有误", DateHelper.dateTime(DateHelper.calculate("2015-01-29 08:32:00",
                -35, "08:00-21:00")), "2015-01-29 08:31:25");
        assertEquals("计算有误", DateHelper.dateTime(DateHelper.calculate("2015-01-29 08:00:30",
                -35, "08:00-21:00")), "2015-01-28 20:59:55");
        assertEquals("计算有误", DateHelper.dateTime(DateHelper.calculate("2016-03-08 14:37:03",
                16 * 60 * 60, "08:00-21:00")), "2016-03-09 17:37:03");
        assertEquals("计算有误", DateHelper.dateTime(DateHelper.calculate("2015-01-29 08:00:30",
                Integer.parseInt("-" + (35 + 3600 * 13)), "08:00-21:00")), "2015-01-27 20:59:55");
        assertEquals("计算有误", DateHelper.dateTime(DateHelper.calculate("2015-01-29 21:30:30",
                35 + 3600 * 14, "08:00-21:00")), "2015-01-31 09:00:35");
        assertEquals("计算有误", DateHelper.dateTime(DateHelper.calculate("2015-01-29 08:32:00",
                3600 * 14, "08:00-21:00")), "2015-01-30 09:32:00");

    }
}