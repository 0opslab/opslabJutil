package com.opslab.util.coordinates;

import com.opslab.util.map.CoordinateUtil;
import junit.framework.TestCase;

/**
 * 测试经纬度相关的工具类
 */
public class CoordinateUtilTest extends TestCase {

    public void testDistance() throws Exception {
        double distance = CoordinateUtil.distance(121.491909, 31.233234, 121.411994, 31.206134);
        System.out.println("俩点之间相距(米):" + distance);
    }
}