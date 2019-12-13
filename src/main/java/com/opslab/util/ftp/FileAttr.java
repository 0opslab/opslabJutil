package com.opslab.util.ftp;

import java.util.Date;

/**
 * 文件属性
 */
public class FileAttr {
    private String fileName;
    private Date ModifyTime;
    private Long size;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getModifyTime() {
        return new Date(ModifyTime.getTime());
    }

    public void setModifyTime(Date modifyTime) {
        ModifyTime = modifyTime;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
