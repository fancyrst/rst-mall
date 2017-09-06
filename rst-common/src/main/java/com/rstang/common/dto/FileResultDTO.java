package com.rstang.common.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yeyx on 2017/9/6.
 */
public class FileResultDTO {

    private FileResult result = FileResult.FAIL;
    /**
     * 文件相对路径
     */
    private String filePath;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 图片宽度高度
     */
    private int width;

    /**
     * 图片宽度
     */
    private int height;

    /**
     * 文件类型
     */
    private String flag ;

    /**
     * 文件存储的主键
     */
    private String id ;

    public FileResult getResult() {
        return result;
    }

    public void setResult(FileResult result) {
        this.result = result;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public enum FileResult {
        OK("成功"), FAIL("失败");

        private String resultDesc;

        FileResult(String resultDesc) {
            this.resultDesc = resultDesc;
        }
        public String getResultDesc() {
            return resultDesc;
        }

        public void setResultDesc(String resultDesc) {
            this.resultDesc = resultDesc;
        }
    }


}
