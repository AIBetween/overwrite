package com.sf.core.datacontrol.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 描述:
 * <p/>
 * Created by 828477[JAX] on 2016/3/8 11:03.
 */
public class HTablePool {

    /**
     * hbase 连接缓存map， 每一个表缓存一个连接
     */
    private Map<String, Connection> connectionPoolCache = new HashMap<String, Connection>();
    /**
     * hbase 缓存配置文件
     */
    private static Configuration configuration;
    private static final Logger logger = LoggerFactory.getLogger(HTablePool.class);
    private static final HTablePool instance = new HTablePool();

    static {

        configuration = new Configuration();
        configuration.set("fs.default.name", "hdfs://cnsz03pl0158:8020");
        configuration.set("hbase.rootdir", "hdfs://hbasecluster/hbase");
        configuration.set("hbase.zookeeper.quorum", "cnsz03pl0158,cnsz03pl0159,cnsz03pl0160");
        configuration.set("hbase.zookeeper.property.dataDir", "/etc/zookeeper/conf");
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.client.max.perserver.tasks", "50");
        configuration.set("hbase.client.max.perregion.tasks", "10");
    }

    private Connection getConnection() {

        try {
            return ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {

            logger.error("get hbase connection error", e);
            throw new RuntimeException(e);
        }
    }

    public Optional<Table> getTableConnection(String tableNameStr) {

        TableName tableName = TableName.valueOf(tableNameStr);
        if (!connectionPoolCache.containsKey(tableNameStr)) {
            //不存在连接，那么新建一个连接，缓存起来，然后返回table。
            Connection connection = getConnection();
            connectionPoolCache.put(tableNameStr, connection);

        }

        try {
            Table table = connectionPoolCache.get(tableNameStr).getTable(tableName);
            return Optional.of(table);
        } catch (IOException e) {
            logger.error("tableName:{}, hbase get error!", e);
            return Optional.empty();
        }
    }

    public static void closeTable(Table table) {

        try {
            table.close();
        } catch (IOException e) {
            logger.error("table close fail and error!", e);
        }
    }

    public static HTablePool getInstance() {

        return instance;
    }

    public static void main(String[] args) {

        Connection connection = getInstance().getConnection();

        Optional<Table> tableOptional = getInstance().getTableConnection("jax_test");
        tableOptional.ifPresent(table -> {

            Get get = new Get(Bytes.toBytes("row1"));
            Result result = null;
            try {
                result = table.get(get);
            } catch (IOException e) {
                logger.error("get error");
            }finally {
                HTablePool.closeTable(tableOptional.get());
            }

            System.out.println(Bytes.toString(result.getValue(Bytes.toBytes("username"), Bytes.toBytes("username"))));
        });


    }

}
