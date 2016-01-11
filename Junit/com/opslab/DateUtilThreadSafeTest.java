package com.opslab;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @Description: 测试Deep.DateUtil的线程安全性
 */
public class DateUtilThreadSafeTest extends Thread {

    public static void main(String[] args) {
        PrintUtil.print("线程安全测试");
        for (int i = 0; i < 5; i++) {
            (new DateUtilThreadSafeTest()).start();
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
                Date date = DateUtil.DateTime("2013-05-24 06:02:20");
                //PrintUtil.print(this.getName()+":"+DateUtil.DateTime(date));
                assertEquals("计算有误", "2013-05-24 06:02:20", DateUtil.DateTime(date));

            } catch (ParseException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}
