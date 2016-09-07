package com.opslab.util.image;

import java.awt.Color;
/**
 * 颜色相关的工具类
 */
public final class ColorUtil {
    /**
     * 16进制转Color对象
     *      color:RGB颜色
     * @param str
     * @return
     */
    public final static Color String2Color(String str) {
        int i =   Integer.parseInt(str.substring(1), 16);
        return new Color(i);
    }

    /**
     * Color对象转16进制
     * @param color
     * @return
     */
    public final static String Color2String(Color color) {
        String R = Integer.toHexString(color.getRed());
        R = R.length()<2?('0'+R):R;
        String B = Integer.toHexString(color.getBlue());
        B = B.length()<2?('0'+B):B;
        String G = Integer.toHexString(color.getGreen());
        G = G.length()<2?('0'+G):G;
        return '#'+R+B+G;
    }
}
