package com.opslab.helper;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * 测试线程安全性
 */
public class DateHeplerThreadSafe extends Thread {

    public static void main(String[] args) {
        System.out.println("线程安全测试");
        for (int i = 0; i < 5; i++) {
            (new DateHeplerThreadSafe()).start();
        }
        System.out.println("主线程运行结束");
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 10000) {
            try {
                this.join(50);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            try {
                Date date = DateHelper.dateTime("2013-05-24 06:02:20");
                //System.out.println(this.getName()+":"+DateHelper.DateTime(date));
                assertEquals("计算有误", "2013-05-24 06:02:20", DateHelper.dateTime(date));

            } catch (ParseException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}
