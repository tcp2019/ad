package com.youzan.ad.mysql;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.youzan.ad.mysql.listener.AggregationListener;
import com.youzan.ad.mysql.listener.AggregationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author TCP
 * @create 2019/4/17 9:14
 */
@Component
public class BinlogClient {
    @Autowired
    private BinlogConfig binlogConfig;

    @Autowired
    private AggregationListener aggregationListener;

    /**
     * 通过实现CommandLineRunner接口来执行该方法在启动项目的时候也启动子线程
     * 该方法作为一个子线程去启东，不阻塞主线程
     */
    public void connect() {
        /*BinaryLogClient client = new BinaryLogClient(
                binlogConfig.getHost(),
                binlogConfig.getPort(),
                binlogConfig.getUsername(),
                binlogConfig.getPassword());
        client.registerEventListener(arrregationListener);
        try {
            client.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        new Thread(
                () -> {
                    BinaryLogClient client = new BinaryLogClient(
                            binlogConfig.getHost(),
                            binlogConfig.getPort(),
                            binlogConfig.getUsername(),
                            binlogConfig.getPassword());
                    client.registerEventListener(aggregationListener);
                    try {
                        client.connect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();
    }
}
