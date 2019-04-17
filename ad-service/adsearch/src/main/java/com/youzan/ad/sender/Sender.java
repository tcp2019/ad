package com.youzan.ad.sender;

import com.youzan.ad.mysql.dto.MySqlRowData;

/**
 * @Author TCP
 * @create 2019/4/17 10:18
 */
public interface Sender {
    void send(MySqlRowData mySqlRowData);
}
