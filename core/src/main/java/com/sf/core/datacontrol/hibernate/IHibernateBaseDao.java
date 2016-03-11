package com.sf.core.datacontrol.hibernate;

import com.sf.core.exception.DataServiceAccessException;

import java.util.List;

/**
 * 描述:
 * <p/>
 * Created by 828477[JAX] on 2016/3/11 15:53.
 */
public interface IHibernateBaseDao<T> {

    void add(T value) throws DataServiceAccessException;

    void addAll(List<T> values) throws DataServiceAccessException;

    void delete(T value) throws DataServiceAccessException;

    void deleteAll(List<T> values) throws DataServiceAccessException;

    void update(T value) throws DataServiceAccessException;

    boolean updateAll(List<T> values) throws DataServiceAccessException;

    T find(Class<T> clazz, int id) throws DataServiceAccessException;

    public List<T> findByOneProperty(Class<T> clazz, String propertyName, String propertyValue) throws DataServiceAccessException;
}
