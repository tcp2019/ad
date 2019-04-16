package com.youzan.ad.mysql.dto;

import com.youzan.ad.mysql.constant.OptionType;
import com.youzan.ad.utils.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author TCP
 * @create 2019/4/12 8:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParseTemplate {
    /**
     * 数据库名称
     */
    private String dataBase;
    /**
     * key：表名
     * value:表模板对象
     */
    private Map<String, TableTemplate> tableTemplateMap = new HashMap<>();

    /**
     * 解析模板文件
     *
     * @param template
     */
    public static ParseTemplate parse(Template template) {
        //创建parseTemple对象
        ParseTemplate parseTemple = new ParseTemplate();
        //将模板文件数据库名称存到parseTemple
        String dataBase = template.getDataBase();
        parseTemple.dataBase = dataBase;
        List<JsonTable> tableList = template.getTableList();
        Map<OptionType, List<String>> optionTypeFieldMap = new HashMap<>();
        List<String> list = new ArrayList<>();
        TableTemplate tableTemplate = new TableTemplate();
        for (JsonTable jsonTable : tableList) {
            //将表名存到表模板对象里面
            String tableName = jsonTable.getTableName();
            tableTemplate.setTableName(tableName);
            tableTemplate.setLevel(jsonTable.getLevel());
            //将表名和表模板对象以key-value形式存到tableTemplateMap里面
            parseTemple.tableTemplateMap.put(tableName, tableTemplate);
            for (JsonTable.Column column : jsonTable.getInsert()) {
                CommonUtil.getValue(OptionType.ADD, optionTypeFieldMap, ArrayList::new).add(column.getColumn());
            }
            for (JsonTable.Column column : jsonTable.getUpdate()) {
                CommonUtil.getValue(OptionType.UPDATE, optionTypeFieldMap, ArrayList::new).add(column.getColumn());
            }
            for (JsonTable.Column column : jsonTable.getDelete()) {
                CommonUtil.getValue(OptionType.DELETE, optionTypeFieldMap, ArrayList::new).add(column.getColumn());
            }
        }
        return parseTemple;
    }
}
