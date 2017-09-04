package com.rstang.core.service;

import com.rstang.core.page.PageView;
import com.rstang.core.persistence.CrudDao;
import com.rstang.core.persistence.DataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yeyx on 2017/8/30.
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>> {
    /**
     * 持久层对象
     */
    @Autowired
    protected D dao;

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public T get(String id) {
        return dao.get(id);
    }

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    public T get(T entity) {
        return dao.get(entity);
    }

    /**
     *  查询列表数据
     * @param entity
     * @return
     */
    public List<T> findList(T entity) {
        return dao.findList(entity);
    }

    /**
     * 查询分页数据
     * @param pageView 分页对象
     * @param entity
     * @return
     */
    public PageView<T> findPage(PageView<T> pageView, T entity) {
        entity.setPageView(pageView);
        pageView.setList(dao.findList(entity));
        return pageView;
    }

    /**
     * 保存数据（插入或更新）
     * @param entity
     */
    @Transactional(readOnly = false)
    public void save(T entity) {
        if (entity.getIsNewRecord()){
            entity.preInsert();
            dao.insert(entity);
        }else{
            entity.preUpdate();
            dao.update(entity);
        }
    }

    /**
     * 删除数据
     * @param entity
     */
    @Transactional(readOnly = false)
    public void delete(T entity) {
        dao.delete(entity);
    }
}
