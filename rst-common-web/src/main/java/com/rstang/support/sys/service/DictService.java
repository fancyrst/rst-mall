package com.rstang.support.sys.service;

import com.rstang.core.service.CrudService;
import com.rstang.support.sys.dao.DictDao;
import com.rstang.support.sys.entity.Dict;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据字典服务类
 * Created by yeyx on 2017/8/30.
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {


    @Transactional(readOnly = false)
    public void save(Dict dict) {
        super.save(dict);

    }

    @Transactional(readOnly = false)
    public void delete(Dict dict) {
        super.delete(dict);

    }

}
