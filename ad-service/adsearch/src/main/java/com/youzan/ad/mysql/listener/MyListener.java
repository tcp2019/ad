package com.youzan.ad.mysql.listener;

import com.youzan.ad.mysql.dto.BinlogRowData;

/**
 * @Author TCP
 * @create 2019/4/12 14:09
 */
public interface MyListener {
    void register();

    void onEvent(BinlogRowData binlogRowData);
}
