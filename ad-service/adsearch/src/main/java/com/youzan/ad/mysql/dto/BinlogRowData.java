package com.youzan.ad.mysql.dto;

import com.github.shyiko.mysql.binlog.event.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @Author TCP
 * @create 2019/4/12 14:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BinlogRowData {
    private TableTemplate tableTemplate;
    private EventType eventType;

    private List<Map<String, String>> before;
    /**
     * 泛型为map类型
     * key:索引对应的字段名称
     * value:索引对应字段的值
     */
    private List<Map<String, String>> after;
}
