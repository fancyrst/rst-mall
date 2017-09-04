package com.rstang.support.sys.dao;

import com.rstang.core.persistence.CrudDao;
import com.rstang.support.sys.entity.Dict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 字典DAO接口
 * Created by yeyx on 2017/8/30.
 */
@Mapper
public interface DictDao extends CrudDao<Dict> {

}
