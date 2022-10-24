package com.opslab.helper;


import com.opslab.helper.DateHelper;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DateHeplerTest {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Test
    public void testHour() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        Date date = DateHelper.hour(DateHelper.dateTime(str1), -0.5F);
        assertEquals("转换有误", "2015-02-23 18:24:00", DateHelper.formatDateTime(date));
    }

    @Test
    public void testHour1() throws Exception {
        Date date = DateHelper.hour(-0.5F);
        assertTrue("计算错误", 30 == DateHelper.subtractMinute(date, new Date()));
    }


    @Test
    public void testSubtract() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2015-02-23 18:55:22";
        long rs = DateHelper.subtract(str1, str2);
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
        long rs = DateHelper.subtractMinute(str1, str2);
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
        long rs = DateHelper.subtractHour(str1, str2);
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
        long rs = DateHelper.subtractDay(str1, str2);
        assertEquals("计算有误", 31, rs);
    }

    @Test
    public void testSubtractDay1() throws Exception {
        String str1 = "2015-03-23 18:54:00";
        String str2 = "2015-03-25 21:55:22";
        long rs = DateHelper.subtractDay(str1, str2);
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
        assertEquals("计算有误", 0, DateHelper.subDay("2015-01-05 13:00:00", "2015-01-05 13:00:00"));
        assertEquals("计算有误", 1, DateHelper.subDay("2015-01-05 13:00:00", "2015-01-06 10:00:00"));
        assertEquals("计算有误", 59, DateHelper.subDay("2015-01-05 13:00:00", "2015-03-05 13:00:00"));
        assertEquals("计算有误", 2, DateHelper.subDay("2015-01-05 13:00:00", "2015-01-07 13:00:00"));
        assertEquals("计算有误", 1823, DateHelper.subDay("2020-01-05 13:00:00", "2015-01-08 13:00:00"));
    }

    @Test
    public void testSubtimeBurst() throws ParseException {
        String timeBurst = "08:00:00-21:00:00";
        assertEquals("计算有误", DateHelper.subtimeBurst("2015-06-24 08:00:00",
                "2015-06-23 20:24:00", timeBurst), -2160);
        assertEquals("计算有误", DateHelper.subtimeBurst("2015-01-05 08:00:00",
                "2015-01-05 08:00:30", timeBurst), 30);

        assertEquals("计算有误", DateHelper.subtimeBurst("2015-01-05 20:59:01",
                "2015-01-05 21:00:00", timeBurst), 59);

        assertEquals("计算有误", DateHelper.subtimeBurst("2015-01-05 21:00:00",
                "2015-01-05 22:00:00", timeBurst), 0);

        assertEquals("计算有误", DateHelper.subtimeBurst("2015-01-05 20:59:02",
                "2015-01-06 21:00:00", timeBurst), 58L + (21 - 8) * 60 * 60);

        assertEquals("计算有误", DateHelper.subtimeBurst("2015-01-05 20:59:02",
                "2015-01-07 08:00:57", timeBurst), (21 - 8) * 3600 + 115L);
        assertEquals("计算有误", DateHelper.subtimeBurst("2016-03-08 14:37:03",
                "2016-03-09 09:37:03", "08:30:00-17:30:00"), 4 * 60 * 60);
    }

    @Test
    public void testCalculate23() {

        assertEquals("计算有误",
                DateHelper.date(DateHelper.calculate("2016-03-08 14:38:23", 2 * 3600, "08:30:00-17:30:00")),
                "2016-03-08");
    }

    @Test
    public void testCalculate() {
        String timeBurst = "08:00:00-21:00:00";
        String messageError = "时间段计算有误";

        Date date = DateHelper.calculate("2015-01-29 20:59:50", 9, timeBurst);
        assertEquals(messageError, DateHelper.formatDateTime(date), "2015-01-29 20:59:59");
        assertEquals(messageError, DateHelper.formatDateTime(DateHelper.calculate("2015-01-29 20:59:50",
                11, timeBurst)), "2015-01-30 08:00:01");
        assertEquals(messageError, DateHelper.formatDateTime(DateHelper.calculate("2015-01-29 20:59:50",
                (3600 * 13 + 1), timeBurst)), "2015-01-30 20:59:51");
        //assertEquals(messageError, DateHelper.format(DateHelper.calculate("2015-01-29 20:59:50",
        //        (3600 * 13 * 3 + 1), timeBurst)), "2015-02-01 20:59:51");
        assertEquals(messageError, DateHelper.formatDateTime(DateHelper.calculate("2015-01-29 20:59:50",
                (3600 * 13 * 3 + 11), timeBurst)), "2015-02-02 08:00:01");

        assertEquals(messageError, DateHelper.formatDateTime(DateHelper.calculate("2015-01-29 08:32:00",
                -35, timeBurst)), "2015-01-29 08:31:25");
        assertEquals(messageError, DateHelper.formatDateTime(DateHelper.calculate("2015-01-29 08:00:30",
                -35, timeBurst)), "2015-01-28 20:59:55");
        assertEquals(messageError, DateHelper.formatDateTime(DateHelper.calculate("2016-03-08 14:37:03",
                16 * 60 * 60, timeBurst)), "2016-03-09 17:37:03");
        assertEquals(messageError, DateHelper.formatDateTime(DateHelper.calculate("2015-01-29 08:00:30",
                Integer.parseInt("-" + (35 + 3600 * 13)), timeBurst)), "2015-01-27 20:59:55");
        assertEquals(messageError, DateHelper.formatDateTime(DateHelper.calculate("2015-01-29 21:30:30",
                35 + 3600 * 14, timeBurst)), "2015-01-31 09:00:35");
        assertEquals(messageError, DateHelper.formatDateTime(DateHelper.calculate("2015-01-29 08:32:00",
                3600 * 14, timeBurst)), "2015-01-30 09:32:00");

    }
}