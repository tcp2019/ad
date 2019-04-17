package com.youzan.ad.mysql.listener;

import com.youzan.ad.index.DataLevel;
import com.youzan.ad.mysql.constant.Constant;
import com.youzan.ad.mysql.constant.OptionType;
import com.youzan.ad.mysql.dto.BinlogRowData;
import com.youzan.ad.mysql.dto.MySqlRowData;
import com.youzan.ad.mysql.dto.TableTemplate;
import com.youzan.ad.sender.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author TCP
 * @create 2019/4/17 9:39
 */
@Component
public class IncrementListener implements MyListener {
    @Autowired
    private AggregationListener aggregationListener;

    @Resource(name = "indexSender")
    private Sender sender;

    @Override
    public void register() {
        Constant.table2db.forEach(
                (k, v) ->
                        aggregationListener.register(v, k, this)
        );
    }

    @Override
    public void onEvent(BinlogRowData binlogRowData) {
        TableTemplate tableTemplate = binlogRowData.getTableTemplate();
        String level = tableTemplate.getLevel();
        //组装要投递的数据
        MySqlRowData mySqlRowData = new MySqlRowData();
        mySqlRowData.setLevel(level);
        mySqlRowData.setTableName(tableTemplate.getTableName());
        //将binlog事件类型转换为自定义类型
        mySqlRowData.setOptionType(OptionType.to(binlogRowData.getEventType()));
        //判断如果事件类型是否是修改类型
        List<String> optionTypeFieldMap = tableTemplate.getOptionTypeFieldMap().get(OptionType.to(binlogRowData.getEventType()));
        if (optionTypeFieldMap == null) {
            return;
        }
        List<Map<String, String>> fieldValueMap = mySqlRowData.getFieldValueMap();
        List<Map<String, String>> after = binlogRowData.getBefore();
        for (Map<String, String> map : after) {
            Map<String, String> _after = new HashMap<>();
            for (Map.Entry<String, String> stringEntry : map.entrySet()) {
                //字段名
                String colName = stringEntry.getKey();
                //对应的值
                String colValue = stringEntry.getValue();
                _after.put(colName, colValue);
            }
            fieldValueMap.add(_after);
        }
        sender.send(mySqlRowData);
    }
}
