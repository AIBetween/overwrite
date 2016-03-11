package com.sf.core.datacontrol.hibernate;

import com.sf.core.exception.DataServiceAccessException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 描述:
 * <p/>
 * Created by 828477[JAX] on 2016/3/11 15:59.
 */
@Component("hibernateService")
public class HibernateService<T> implements IHibernateBaseDao<T> {


    private SessionFactory sessionFactory;


    @Resource
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession()
    {
        return sessionFactory.getCurrentSession();
    }

    public void add(T value) {

        Serializable serializable;

        try {
            serializable = getCurrentSession().save(value);
        } catch (Exception e) {

            throw new DataServiceAccessException(e);
        }

    }

    public void addAll(List<T> values) {

        List<Serializable> results;
        try {

            results = values.parallelStream()
                    .map(value -> getCurrentSession().save(value))
                    .collect(Collectors.toList());

        } catch (Exception e) {

            throw new DataServiceAccessException(e);
        }


    }

    public void delete(T value) {

        try {
            getCurrentSession().delete(value);
        } catch (Exception e) {

            throw new  DataServiceAccessException(e);
        }
    }

    public void deleteAll(List<T> values) {

    }

    public void update(T value) {

    }

    public boolean updateAll(List<T> values) {
        return false;
    }

    public T find(Class<T> clazz, int id) {
        return null;
    }


    public List<T> findByOneProperty(Class<T> clazz, String propertyName, String propertyValue){

        List<T> resultList;
        try {

            return (List<T>) getCurrentSession().createCriteria(clazz)
                    .add(Restrictions.eq(propertyName, propertyValue))
                    .list();
        } catch (Exception e) {

            throw new DataServiceAccessException(e);
        }
    }
}
