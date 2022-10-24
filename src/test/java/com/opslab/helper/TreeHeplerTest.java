package com.opslab.helper;

import com.opslab.bean.TreeBean;
import com.opslab.util.JacksonUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TreeHeplerTest {

    /**
     * 测试将一组具有父子节点的关系的数据，整理成树形结构
     */
    @Test
    public void testDeptree(){
        ArrayList<TreeBean> lists = new ArrayList<>();

        lists.add(new TreeBean("0", "root", ""));
        lists.add(new TreeBean("10", "node10", "0"));
        lists.add(new TreeBean("101", "node101", "10"));
        lists.add(new TreeBean("102", "node102", "10"));
        lists.add(new TreeBean("1021", "node102", "102"));
        lists.add(new TreeBean("20", "node20", "0"));
        lists.add(new TreeBean("202", "node20", "20"));


        List<TreeBean> tree = TreeHepler.getDeptTree(lists);
        System.out.println(JacksonUtil.toJson(tree));


        List<TreeBean> tree1 = TreeHepler.getTree(lists, "");
        System.out.println(JacksonUtil.toJson(tree1));
    }

}