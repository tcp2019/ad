package com.youzan.ad.handler;

import com.youzan.ad.dump.table.*;
import com.youzan.ad.index.DataTable;
import com.youzan.ad.index.IndexAware;
import com.youzan.ad.index.adcreative.AdCreativeIndex;
import com.youzan.ad.index.adcreative.AdCreativeObject;
import com.youzan.ad.index.adcreativeunit.AdCreativeUnitIndex;
import com.youzan.ad.index.adcreativeunit.AdCreativeUnitObject;
import com.youzan.ad.index.adplan.AdPlanIndex;
import com.youzan.ad.index.adplan.AdPlanObject;
import com.youzan.ad.index.adunit.AdUnitIndex;
import com.youzan.ad.index.adunit.AdUnitObject;
import com.youzan.ad.index.district.UnitDistrictIndex;
import com.youzan.ad.index.district.UnitDistrictObject;
import com.youzan.ad.index.interest.UnitItIndex;
import com.youzan.ad.index.interest.UnitItObject;
import com.youzan.ad.index.keyword.UnitKeywordIndex;
import com.youzan.ad.index.keyword.UnitKeywordObject;
import com.youzan.ad.mysql.constant.OptionType;
import com.youzan.ad.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashSet;

/**
 * @Author TCP
 * @create 2019/4/3 17:25
 */
@Slf4j
public class AdLevelDataHandler {
    /**
     * 二级查询 广告计划
     *
     * @param adPlanTable
     * @param optionType
     */
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

    /**
     * 二级查询：广告创意
     *
     * @param adCreativeTable
     * @param optionType
     */
    public static void handleLevl2(AdCreativeTable adCreativeTable, OptionType optionType) {
        //将adCreativeTable 转换为adCreativeObject
        AdCreativeObject adCreativeObject = new AdCreativeObject(
                adCreativeTable.getCreateiveId(),
                adCreativeTable.getName(),
                adCreativeTable.getType(),
                adCreativeTable.getMaterialType(),
                adCreativeTable.getHeight(),
                adCreativeTable.getWidth(),
                adCreativeTable.getAuditStatus(),
                adCreativeTable.getCreateTime(),
                adCreativeTable.getUpdateTime());
        handlerBinLogEvent(DataTable.of(
                AdCreativeIndex.class),
                adCreativeObject.getCreateiveId(),
                adCreativeObject,
                optionType);
    }

    //三级查询 :推广单元
    public static void handleLevel3(AdUnitTable adUnitTable, OptionType optionType) {
        //判断adUnitTable对应的计划是否存在
        AdPlanObject adPlanObject = DataTable.of(AdPlanIndex.class).get(adUnitTable.getPlanId());
        if (adPlanObject == null) {
            log.error("this planId is invalidate");
            return;
        }
        AdUnitObject adUnitObject = new AdUnitObject(
                adUnitTable.getUnitId(),
                adUnitTable.getUnitStatus(),
                adUnitTable.getPositionType(),
                adUnitTable.getPlanId()
        );
        handlerBinLogEvent(DataTable.of(
                AdUnitIndex.class),
                adUnitObject.getUnitId(),
                adUnitObject,
                optionType);
    }

    /**
     * 三级查询：创意-推广单元
     *
     * @param adCreativeUnitTable
     * @param optionType
     */
    public static void handleLevel3(AdCreativeUnitTable adCreativeUnitTable, OptionType optionType) {
        //判断当前type是否是update
        if (optionType == OptionType.UPDATE) {
            log.error("no support update");
            return;
        }
        //判断推广计划是否为空
        AdPlanObject adPlanObject = DataTable.of(AdPlanIndex.class).get(adCreativeUnitTable.getCreativeId());
        if (adPlanObject == null) {
            log.error("this creativeId is invalidate");
            return;
        }
        //判断推广单元是否为空
        AdUnitObject adUnitObject = DataTable.of(AdUnitIndex.class).get(adCreativeUnitTable.getUnitId());
        if (adUnitObject == null) {
            log.error("this unitId is invalidate");
            return;
        }
        AdCreativeUnitObject adCreativeUnitObject = new AdCreativeUnitObject(
                adCreativeUnitTable.getCreativeId(),
                adCreativeUnitTable.getUnitId()
        );
        handlerBinLogEvent(
                DataTable.of(AdCreativeUnitIndex.class),
                CommonUtil.stringConcat(
                        adCreativeUnitObject.getCreativeId().toString(),
                        adCreativeUnitObject.getUnitId().toString()),
                adCreativeUnitObject,
                optionType);
    }

    /**
     * 四级查询  地域限制
     *
     * @param unitDistrictTable
     * @param optionType
     */
    public static void handleLevel4(UnitDistrictTable unitDistrictTable, OptionType optionType) {
        if (optionType == OptionType.UPDATE) {
            log.error("no support update");
            return;
        }
        //判断推广单元是否为空
        if (DataTable.of(AdUnitIndex.class).get(unitDistrictTable.getUnitId()) == null) {
            log.error("this unitId is invalidate");
            return;
        }
        UnitDistrictObject unitDistrictObject = new UnitDistrictObject(
                unitDistrictTable.getUnitId(),
                unitDistrictTable.getProvince(),
                unitDistrictTable.getCity()
        );
        handlerBinLogEvent(
                DataTable.of(
                        UnitDistrictIndex.class),
                CommonUtil.stringConcat(
                        unitDistrictObject.getProvince(),
                        unitDistrictObject.getCity()
                ),
                new HashSet<>(
                        Collections.singleton(unitDistrictObject.getUnitId())
                ),
                optionType);
    }

    /**
     * 四级查询：兴趣限制
     *
     * @param unitItTable
     * @param optionType
     */
    public static void handleLevel4(UnitItTable unitItTable, OptionType optionType) {
        if (optionType == OptionType.UPDATE) {
            log.error("no support update");
            return;
        }
        //判断推广单元是否为空
        if (DataTable.of(AdUnitIndex.class).get(unitItTable.getUnitId()) == null) {
            log.error("this unitId is invalidate");
            return;
        }
        UnitItObject unitItObject = new UnitItObject(
                unitItTable.getUnitId(),
                unitItTable.getItTag()
        );

        handlerBinLogEvent(
                DataTable.of(
                        UnitItIndex.class),
                unitItObject.getItTag(),
                new HashSet<>(
                        Collections.singleton(unitItObject.getUnitId())
                ),
                optionType);
    }

    /**
     * 四级查询：关键词限制
     *
     * @param unitKeywordTable
     * @param optionType
     */
    public static void handleLevel4(UnitKeywordTable unitKeywordTable, OptionType optionType) {
        if (optionType == OptionType.UPDATE) {
            log.error("no support update");
            return;
        }
        //判断推广单元是否为空
        if (DataTable.of(AdUnitIndex.class).get(unitKeywordTable.getUnitId()) == null) {
            log.error("this unitId is invalidate");
            return;
        }
        UnitKeywordObject unitKeywordObject = new UnitKeywordObject(
                unitKeywordTable.getKeyword(),
                unitKeywordTable.getUnitId()
        );


        handlerBinLogEvent(
                DataTable.of(
                        UnitKeywordIndex.class),
                unitKeywordObject.getKeyword(),
                new HashSet<>(
                        Collections.singleton(unitKeywordObject.getUnitId())
                ),
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
