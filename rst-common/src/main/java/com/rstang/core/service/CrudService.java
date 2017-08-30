package com.rstang.core.service;

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
     * �־ò����
     */
    @Autowired
    protected D dao;

    /**
     * ��ȡ��������
     * @param id
     * @return
     */
    public T get(String id) {
        return dao.get(id);
    }

    /**
     * ��ȡ��������
     * @param entity
     * @return
     */
    public T get(T entity) {
        return dao.get(entity);
    }

    /**
     * ��ѯ�б�����
     * @param entity
     * @return
     */
    public List<T> findList(T entity) {
        return dao.findList(entity);
    }

    /**
     * ��ѯ��ҳ����
     * @param page ��ҳ����
     * @param entity
     * @return

    public Page<T> findPage(Page<T> page, T entity) {
        entity.setPage(page);
        page.setList(dao.findList(entity));
        return page;
    }*/

    /**
     * �������ݣ��������£�
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
     * ɾ������
     * @param entity
     */
    @Transactional(readOnly = false)
    public void delete(T entity) {
        dao.delete(entity);
    }
}
