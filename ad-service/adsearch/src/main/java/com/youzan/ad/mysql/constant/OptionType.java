package com.youzan.ad.mysql.constant;


import com.github.shyiko.mysql.binlog.event.EventType;

import javax.print.attribute.standard.MediaSize;

/**
 * @Author TCP
 * @create 2019/4/3 17:22
 */
public enum OptionType {
    ADD,
    UPDATE,
    DELETE,
    OTHER;

    /**
     * 将binlog操作类型转换为自定义操作类型
     *
     * @param eventType
     * @return
     */
    public static OptionType to(EventType eventType) {
        switch (eventType) {
            case EXT_WRITE_ROWS:
                return OptionType.ADD;
            case EXT_UPDATE_ROWS:
                return OptionType.UPDATE;
            case EXT_DELETE_ROWS:
                return OptionType.DELETE;
            default:
                return OptionType.OTHER;
        }
    }
}
