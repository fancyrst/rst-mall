package com.rstang.common.dto;

import com.rstang.common.em.AppInfo;
import com.rstang.common.sao.FileOperateSao;

/**
 * Created by yeyx on 2017/9/5.
 */
public class FileInfoDTO {
    /** 应用编码,必填 **/
    private AppInfo appCode;
    /** 文件名称,必填 **/
    private String fileName;

    /** 文件大小,选填 **/
    private long fileSize ;
    /** 文件的字节数组,必填 **/
    private byte[] fileBytes;

    private String createUser;

    /** 文件所属模块,必填 **/
    private FileOperateSao.BelongModule module;
    /** 文件类型,必填 **/
    private FileOperateSao.FileType fileType;

    public AppInfo getAppCode() {
        return appCode;
    }

    public void setAppCode(AppInfo appCode) {
        this.appCode = appCode;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public FileOperateSao.BelongModule getModule() {
        return module;
    }

    public void setModule(FileOperateSao.BelongModule module) {
        this.module = module;
    }

    public FileOperateSao.FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileOperateSao.FileType fileType) {
        this.fileType = fileType;
    }
}
