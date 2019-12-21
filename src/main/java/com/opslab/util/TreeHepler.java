package com.opslab.util;

import com.opslab.bean.TreeBean;

import java.util.ArrayList;
import java.util.List;

public class TreeHepler {
    /**
     * 将具有父子关系的list集合转换为树形结构
     *
     * @param list
     * @return
     */
    public static List<TreeBean> getDeptTree(List<TreeBean> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        return getJsonCity(list, null);
//        List<TreeBean> parent = new ArrayList<TreeBean>();
//        List<TreeBean> child = new ArrayList<TreeBean>();
//
//        for (TreeBean bean : list) {
//            String parenId = bean.getParentId();
//            if (parenId != null && parenId.trim().length() > 0) {
//                child.add(bean);
//            } else {
//                parent.add(bean);
//            }
//        }
//        System.out.println(JacksonUtil.toJSON(parent));
//        System.out.println(JacksonUtil.toJSON(child));

//        for (TreeBean pbean:parent){
//            for (TreeBean cbean:child){
//                if(cbean.getParentId().equals(pbean.getTreeId())){
//                    pbean.getChildren().add(cbean);
//                }
//            }
//        }
//        System.out.println(JacksonUtil.toJSON(parent));
//
//        for (TreeBean pbean:parent){
//            for (TreeBean pPbean:parent){
//                if(pbean.getTreeId().equals(pPbean.getParentId())){
//                    pbean.getChildren().add(pPbean);
//                }
//            }
//        }
//        return parent;

    }

    /**
     * 返回父子关联的结构化的数据
     * @param pList
     * @param cList
     * @return
     */
    /**
     * 返回父子关联的结构化的数据
     *
     * @return
     */
    public static List<TreeBean> getJsonCity(List<TreeBean> list, List<TreeBean> reList) {

        TreeBean pcity = new TreeBean();
        TreeBean city = new TreeBean();
        List<TreeBean> listCity = new ArrayList<TreeBean>();
        List<TreeBean> listPCity = new ArrayList<TreeBean>();
        //默认每一个都是父节点
        for (int i = 0; i < list.size(); i++) {
            pcity = list.get(i);
            //默认每一个都是子节点
            for (int j = 0; j < list.size(); j++) {
                city = list.get(j);
                //判断每个子节点的父id等于父节点id并将对象放入list中
                if (pcity.getTreeId().equals(city.getParentId())) {
                    listCity.add(city);
                }
            }
            //判断当前父节点是否有子节点
            if (listCity != null && listCity.size() != 0) {


                /**
                 *注意 实体类中属性不能是list 否则将对象添加进list中这个属性的数据会丢失
                 *这是个坑 后来我用数组解决了
                 */
                //dpcity.setChildren(listCity);


                //将获取的子节点list放入数组中
                List<String> keys = new ArrayList<>();
                TreeBean[] childrens = pcity.getChildrens();
                int size = listCity.size();
                int ssize = 0;
                if(childrens != null && childrens.length > 0){
                    size += childrens.length;
                    ssize = childrens.length;
                }

                TreeBean[] childs = new TreeBean[size];

                for (int j = 0; j < ssize; j++) {
                    TreeBean bean = childrens[j];
                    if(bean != null){
                        String key = bean.getTreeId();
                        if(!keys.contains(key)){
                            childs[j]=bean;
                            keys.add(key);
                        }
                    }
                }

                for (int j = 0; j < listCity.size(); j++) {
                    TreeBean bean = listCity.get(j);
                    if(bean != null){
                        String key = bean.getTreeId();
                        if(!keys.contains(key)){
                            childs[ssize+j]=listCity.get(j);
                            keys.add(key);
                        }
                    }

                }

                pcity.setChildrens(deleteArrayNull(childs));

                //清空list
                listCity.clear();
                //将新对象放入list中
                listPCity.add(pcity);
            }
        }
        //判断父节点list是否是空
        if (listPCity != null && listPCity.size() != 0) {
            //为递归返参做准备
            reList = listPCity;
            //递归 解决父节点list中还要有父子节点关系
            return getJsonCity(listPCity, reList);
        } else {
            return reList;
        }

    }

    public static TreeBean[] deleteArrayNull(TreeBean[] array) {
        List<TreeBean> tmp = new ArrayList<TreeBean>();
        for(TreeBean str:array){
            if(str!=null){
                tmp.add(str);
            }
        }
        return tmp.toArray(new TreeBean[0]);
    }

}
