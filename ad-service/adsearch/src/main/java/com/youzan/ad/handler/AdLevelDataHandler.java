package com.youzan.ad.handler;

import com.youzan.ad.dump.table.AdPlanTable;
import com.youzan.ad.index.DataTable;
import com.youzan.ad.index.IndexAware;
import com.youzan.ad.index.adplan.AdPlanIndex;
import com.youzan.ad.index.adplan.AdPlanObject;
import com.youzan.ad.mysql.constant.OptionType;

/**
 * @Author TCP
 * @create 2019/4/3 17:25
 */
public class AdLevelDataHandler {

    public static void handleLevel2(AdPlanTable adPlanTable, OptionType optionType) {
        AdPlanObject adPlanObject = new AdPlanObject(
                adPlanTable.getPlanId(),
                adPlanTable.getUserId(),
                adPlanTable.getPlanStatus(),
                adPlanTable.getStartDate(),
                adPlanTable.getEndDate());
        handlerBinLogEvent(
                DataTable.of(AdPlanIndex.class),
                adPlanObject.getPlanId(),
                adPlanObject,
                optionType);

    }

    private static <K, V> void handlerBinLogEvent(
            IndexAware<K, V> indexAware,
            K key,
            V value,
            OptionType optionType
    ) {
        switch (optionType) {
            case ADD:
                indexAware.add(key, value);
                break;
            case UPDATE:
                indexAware.update(key, value);
                break;
            case DELETE:
                indexAware.delete(key, value);
                break;
            default:
                break;
        }
    }
}
