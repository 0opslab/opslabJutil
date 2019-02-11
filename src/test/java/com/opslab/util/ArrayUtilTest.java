package com.opslab.util;


import org.junit.Test;

public class ArrayUtilTest {
    @Test
    public void testdoubleBitCount() {
        int size = 1000000;
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = ((double) 1) / (i + 1);
        }
        ArrayUtil.doubleBitCount(arr);
    }
}