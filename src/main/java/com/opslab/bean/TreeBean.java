package com.opslab.bean;



import java.util.Date;

/**
 * 树形结构
 */

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


    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public TreeBean[] getChildrens() {
        return childrens;
    }

    public void setChildrens(TreeBean[] childrens) {
        this.childrens = childrens;
    }
}
