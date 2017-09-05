package com.rstang.common.em;

/**
 * Created by yeyx on 2017/9/5.
 */
public enum AppInfo {

    MALL("mall", "商城"), AGENT("agent", "代理商"), SYS("sys", "系统基础");

    private String appCode;
    private String appDesc;

    AppInfo(String appCode, String appDesc) {
        this.appCode = appCode;
        this.appDesc = appDesc;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }
}
