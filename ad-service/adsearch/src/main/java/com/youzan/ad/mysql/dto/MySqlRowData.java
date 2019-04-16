package com.youzan.ad.mysql.dto;

import com.youzan.ad.mysql.constant.OptionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author TCP
 * @create 2019/4/16 14:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MySqlRowData {
    private String tableName;

    private String level;

    private OptionType optionType;

    private List<Map<String, String>> fieldValueMap = new ArrayList<>();
}
