package com.youzan.ad.mysql.dto;

import com.youzan.ad.mysql.constant.OptionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author TCP
 * @create 2019/4/12 8:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableTemplate {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 表对应的级别
     */
    private String level;
    /**
     * key：操作类型
     * value：对应的字段
     */
    Map<OptionType, List<String>> optionTypeFieldMap = new HashMap<>();
    /**
     * key：binlog日志对应的索引
     * value：对应表中的字段名
     */
    Map<Integer, String> posMap = new HashMap<>();
}
