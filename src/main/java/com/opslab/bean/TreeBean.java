package com.opslab.bean;


import lombok.Data;

import java.util.Date;

/**
 * 树形结构
 */
@Data
public class TreeBean {
    /*节点ID*/
    private String treeId;
    /*节点名称*/
    private String treeName;
    /*父节点ID*/
    private String parentId;
    /*创建时间*/
    private Date createTime;
    /** 节点下的子节点 */
    private TreeBean[] childrens;

}
