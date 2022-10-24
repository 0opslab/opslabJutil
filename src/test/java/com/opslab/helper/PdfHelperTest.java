package com.opslab.helper;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

@Ignore
public class PdfHelperTest {

    @Test
    public void test1(){
        String file = "D:\\学习资料\\Illustrated Guide to SQLX.pdf";
        String str = PdfHelper.pfd2Base64(new File(file));
        System.out.println(str);
        PdfHelper.base64StringToPdf(str,"D:\\学习资料\\I.pdf");
    }
}