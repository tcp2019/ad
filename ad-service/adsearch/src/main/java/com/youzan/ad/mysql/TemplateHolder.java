package com.youzan.ad.mysql;

import com.alibaba.fastjson.JSON;
import com.netflix.discovery.converters.Auto;
import com.sun.org.apache.xalan.internal.xsltc.runtime.InternalRuntimeError;
import com.youzan.ad.mysql.constant.OptionType;
import com.youzan.ad.mysql.dto.ParseTemplate;
import com.youzan.ad.mysql.dto.TableTemplate;
import com.youzan.ad.mysql.dto.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author TCP
 * @create 2019/4/12 8:44
 */
@Component
@Slf4j
public class TemplateHolder {
    @Autowired
    private static JdbcTemplate jdbcTemplate;
    @Autowired
    private static ParseTemplate parseTemplate;

    /**
     * 解析template.json文件
     *
     * @param path
     */
    public void loadJson(String path) {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        try {
            //将resourceAsStream反序列化为模板对象
            Template template = JSON.parseObject(resourceAsStream, Template.class);
            //对模板对象进行解析
            ParseTemplate parseTemplate = ParseTemplate.parse(template);
            metaData(parseTemplate);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException("fail to parse json file");
        }
    }

    @PostConstruct
    private void init() {
        loadJson("template.json");
    }

    public TableTemplate getTable(String tableName) {
        return parseTemplate.getTableTemplateMap().get(tableName);
    }

    /**
     * 从数据库中查出广告表字段和对应的索引，并以key-value存到tableTemplate.getPosMap()里面
     *
     * @param parseTemplate
     */
    private void metaData(ParseTemplate parseTemplate) {
        String sql = "SELECT table_schema,table_name,column_name,ordinal_position " +
                "FROM information_schema.`COLUMNS` " +
                "WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";
        Map<String, TableTemplate> tableTemplateMap = parseTemplate.getTableTemplateMap();
        for (Map.Entry<String, TableTemplate> map : tableTemplateMap.entrySet()) {
            TableTemplate tableTemplate = map.getValue();
            List<String> insertStringList = tableTemplate.getOptionTypeFieldMap().get(OptionType.ADD);
            List<String> updateStringList = tableTemplate.getOptionTypeFieldMap().get(OptionType.UPDATE);
            List<String> deleteStringList = tableTemplate.getOptionTypeFieldMap().get(OptionType.DELETE);
            jdbcTemplate.query(sql, new Object[]{
                    parseTemplate.getDataBase(), tableTemplate.getTableName()
            }, (resultSet -> {
                int ordinal_position = resultSet.getInt("ordinal_position");
                String column_name = resultSet.getString("column_name");
                if (updateStringList != null && updateStringList.contains(column_name)
                        || (insertStringList != null && insertStringList.contains(column_name))
                        || (deleteStringList != null && deleteStringList.contains(column_name))) {
                    tableTemplate.getPosMap().put(ordinal_position - 1, column_name);
                }
                return null;
            }));
        }
    }

}
