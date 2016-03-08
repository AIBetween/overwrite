package com.sf.core.datacontrol.hbase;

import java.util.List;
import java.util.Set;

/**
 * 描述: hbase 数据dml 操纵接口
 *
 * Created by 828477[JAX] on 2016/3/8 16:07.
 */
public interface IDataService<T> {

    public void put(T value);

    public void put(List<T> values);

    public void delete(String rowKey);

    public void delete(Set<String> rowKeys);

    public void delete(List<T> values);

    public T find(String rowKey);

    /**
     * 根据条件查询一组数据
     * @param key 查询前缀
     * @param useStartRow 是否使用查询前缀，作为开始row
     * @param stopRowKey 结束的rowkey
     * @param useCache 是否使用缓存
     * @param cacheSize 缓存的大小
     * @param minStamp 要查询的最小的时间戳
     * @param maxStamp 要查询的最大的时间戳
     * @param pageSize 一页的大小
     * @return 返回list<T>查询到的数据
     */
    public List<T> find(String key, boolean useStartRow, String stopRowKey,
                        boolean useCache, int cacheSize, long minStamp, long maxStamp,long pageSize);

}
