package com.youzan.ad.mysql.dto;

import com.github.shyiko.mysql.binlog.event.EventType;

import java.util.List;
import java.util.Map;

/**
 * @Author TCP
 * @create 2019/4/12 14:07
 */
public class BinlogRowData {
    private TableTemplate tableTemplate;

    private EventType eventType;

    private List<Map<String, String>> before;

    private List<Map<String, String>> after;
}
