package com.youzan.ad.runner;

import com.youzan.ad.mysql.BinlogClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

/**
 * @Author TCP
 * @create 2019/4/17 9:34
 */
public class BinlogRunner implements CommandLineRunner {
    @Autowired
    private BinlogClient binlogClient;

    @Override
    public void run(String... args) throws Exception {
        binlogClient.connect();
    }
}
