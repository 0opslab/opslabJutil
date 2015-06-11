package evilp0s;


import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DateUtilTest {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Before
    public void setUp() throws Exception {

    }

    @org.junit.Test
    public void testGetDateTime() throws Exception {
        System.out.println(DateUtil.DateTime());
    }

    @org.junit.Test
    public void testGetDate() throws Exception {
        System.out.println(DateUtil.Date());
    }

    @org.junit.Test
    public void testGetTime() throws Exception {
        System.out.println(DateUtil.Time());
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
        Date date = DateUtil.hour(DateUtil.DateTime(str1), -0.5F);
        String rs = DateUtil.DateTime(date);
        assertEquals("转换有误", "2015-02-23 18:24:00", rs);
    }

    @Test
    public void testHour1() throws Exception {
        Date date = DateUtil.hour(-0.5F);
        assertTrue("计算错误", 30 == DateUtil.SubtractMinute(date, new Date()));
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
        long rs = DateUtil.Subtract(str1, str2);
        System.out.println(rs);
        assertEquals("计算有误", 82L, rs);
    }

    @Test
    public void testSubtract1() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2015-02-23 18:55:22";
        long rs = DateUtil.Subtract(sdf.parse(str1), sdf.parse(str2));
        assertEquals("计算有误", 82L, rs);
    }

    @Test
    public void testSubtractMinute() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2015-02-23 18:55:22";
        int rs = DateUtil.SubtractMinute(str1, str2);
        assertEquals("计算有误", 1, rs);
    }

    @Test
    public void testSubtractMinute1() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2015-02-23 18:55:22";
        int rs = DateUtil.SubtractMinute(sdf.parse(str1), sdf.parse(str2));
        assertEquals("计算有误", 1, rs);
    }

    @Test
    public void testSubtractHour() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2015-02-24 21:55:22";
        int rs = DateUtil.SubtractHour(str1, str2);
        assertEquals("计算有误", 27, rs);
    }

    @Test
    public void testSubtractHour1() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2015-02-23 21:55:22";
        int rs = DateUtil.SubtractHour(sdf.parse(str1), sdf.parse(str2));
        assertEquals("计算有误", 27, rs);
    }

    @Test
    public void testSubtractDay() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2015-03-26 21:55:22";
        int rs = DateUtil.SubtractDay(str1, str2);
        assertEquals("计算有误", 31, rs);
    }

    @Test
    public void testSubtractDay1() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2015-03-25 21:55:22";
        int rs = DateUtil.SubtractDay(sdf.parse(str1), sdf.parse(str2));
        assertEquals("计算有误", 2, rs);
    }

    @Test
    public void testSubtractMonth() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2016-08-25 21:55:22";
        int rs = DateUtil.SubtractMonth(str1, str2);
        assertEquals("计算有误", 18, rs);
    }

    @Test
    public void testSubtractMonth1() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2016-08-25 21:55:22";
        int rs = DateUtil.SubtractMonth(sdf.parse(str1), sdf.parse(str2));
        assertEquals("计算有误", 18, rs);
    }

    @Test
    public void testSubtractYear() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2016-08-25 21:55:22";
        int rs = DateUtil.SubtractYear(sdf.parse(str1), sdf.parse(str2));
        assertEquals("计算有误", 1, rs);
    }

    @Test
    public void testSubtractYear1() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2016-08-25 21:55:22";
        int rs = DateUtil.SubtractYear(str1, str2);
        assertEquals("计算有误", 1, rs);
    }

    @Test
    public void testSubtractTime() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2015-02-23 19:59:22";
        String rs = DateUtil.SubtractTime(str1, str2);
        assertEquals("计算有误", "1:5:22", rs);
    }

    @Test
    public void testSubtractDate() throws Exception {
        String str1 = "2015-02-23 18:54:00";
        String str2 = "2015-02-25 19:59:22";
        String rs = DateUtil.SubtractDate(str1, str2);
        assertEquals("计算有误", "2-1:5:22", rs);
    }

    @Test
    public void testSubDay() {
        System.out.println(DateUtil.subDay("2015-01-05 13:00:00", "2015-01-05 13:00:00"));
        System.out.println(DateUtil.subDay("2015-01-05 13:00:00", "2015-01-06 10:00:00"));
        System.out.println(DateUtil.subDay("2015-01-05 13:00:00", "2015-03-05 13:00:00"));
        System.out.println(DateUtil.subDay("2015-01-05 13:00:00", "2015-01-07 13:00:00"));
        System.out.println(DateUtil.subDay("2015-01-05 13:00:00", "2015-01-08 13:00:00"));
    }

    @Test
    public void testSubtimeBurst() throws ParseException {

        assertEquals(
                "计算有误",
                DateUtil.subtimeBurst(
                        "2015-01-05 08:00:00",
                        "2015-01-05 08:00:30",
                        "08:00-21:00"),
                30);
        assertEquals(
                "计算有误",
                DateUtil.subtimeBurst(
                        "2015-01-05 20:59:01",
                        "2015-01-05 21:00:00",
                        "08:00-21:00"),
                59);

        assertEquals(
                "计算有误",
                DateUtil.subtimeBurst(
                        "2015-01-05 21:00:00",
                        "2015-01-05 22:00:00",
                        "08:00-21:00"),
                0);

        assertEquals(
                "计算有误",
                DateUtil.subtimeBurst(
                        "2015-01-05 20:59:02",
                        "2015-01-06 21:00:00",
                        "08:00-21:00"),
                58L + (21 - 8) * 60 * 60);

        assertEquals(
                "计算有误",
                DateUtil.subtimeBurst(
                        "2015-01-05 20:59:02",
                        "2015-01-07 08:00:57",
                        "08:00-21:00"),
                58L + ((21 - 8) * 60 * 60) * 1 + 57L);
    }

    @Test
    public void testCalculate() throws ParseException {
        assertEquals(
                "计算有误",
                DateUtil.DateTime(DateUtil.calculate(
                        "2015-01-29 15:32:00",
                        10,
                        "08:00-21:00")),
                "2015-01-29 15:32:10");

        assertEquals(
                "计算有误",
                DateUtil.DateTime(DateUtil.calculate(
                        "2015-01-29 21:32:00",
                        55,
                        "08:00-21:00")),
                "2015-01-30 08:00:55");

        assertEquals(
                "计算有误",
                DateUtil.DateTime(DateUtil.calculate(
                        "2015-01-29 07:32:00",
                        55,
                        "08:00-21:00")),
                "2015-01-29 08:00:55");

        assertEquals(
                "计算有误",
                DateUtil.DateTime(DateUtil.calculate(
                        "2015-01-29 21:32:00",
                        55 + (21 - 8) * 60 * 60,
                        "08:00-21:00")),
                "2015-01-31 08:00:55");
        assertEquals(
                "计算有误",
                DateUtil.DateTime(DateUtil.calculate(
                        "2015-01-29 07:32:00",
                        -55,
                        "08:00-21:00")),
                "2015-01-28 20:59:05");

        assertEquals(
                "计算有误",
                DateUtil.DateTime(DateUtil.calculate(
                        "2015-01-29 21:32:00",
                        -55,
                        "08:00-21:00")),
                "2015-01-29 20:59:05");
    }
}