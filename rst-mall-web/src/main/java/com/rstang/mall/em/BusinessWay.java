package com.rstang.mall.em;

/**
 * 经营方式
 * Created by yeyx on 2017/9/6.
 */
public enum BusinessWay {

    SELF("M8", "自营商品"), THREE("M6", "第三方商品");
    private String code;
    private String desc;

    BusinessWay(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
