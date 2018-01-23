package com.rstang.support.sys.dao.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SysDict {
    private String id;

    private String value;

    private String label;

    private String type;

    private String description;

    private Integer sort;

    private String parentId;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String remarks;

    private String delFlag;

}