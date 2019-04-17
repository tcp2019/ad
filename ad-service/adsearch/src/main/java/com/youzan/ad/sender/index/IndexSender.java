package com.youzan.ad.sender.index;

import com.alibaba.fastjson.JSON;
import com.youzan.ad.dump.table.*;
import com.youzan.ad.handler.AdLevelDataHandler;
import com.youzan.ad.index.DataLevel;
import com.youzan.ad.index.adplan.AdPlanObject;
import com.youzan.ad.mysql.constant.Constant;
import com.youzan.ad.mysql.dto.MySqlRowData;
import com.youzan.ad.sender.Sender;
import com.youzan.ad.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author TCP
 * @create 2019/4/17 10:19
 */
@Slf4j
@Component("indexSender")
public class IndexSender implements Sender {
    @Override
    public void send(MySqlRowData mySqlRowData) {
        String level = mySqlRowData.getLevel();
        if (level.equals(DataLevel.LEVEL2)) {
            level2RowData(mySqlRowData);
        } else if (level.equals(DataLevel.LEVEL3)) {
            level3RowData(mySqlRowData);
        } else if (level.equals(DataLevel.LEVEL4)) {
            level4RowData(mySqlRowData);
        } else {
            log.info("MySqlRowData Error:{}", JSON.toJSONString(mySqlRowData));
        }
    }

    private void level4RowData(MySqlRowData mySqlRowData) {
        List<Map<String, String>> fieldValueMap = mySqlRowData.getFieldValueMap();
        //如果是关键词限制表
        if (mySqlRowData.getTableName().equals(Constant.AD_UNIT_KEYWORD_TABLE_INFO.TABLE_NAME)) {
            List<UnitKeywordTable> unitKeywordTableList = new ArrayList<>();
            for (Map<String, String> map : fieldValueMap) {
                UnitKeywordTable unitKeywordTable = new UnitKeywordTable();
                map.forEach(
                        (k, v) -> {
                            switch (k) {
                                case Constant.AD_UNIT_KEYWORD_TABLE_INFO.COLUMN_KEYWORD:
                                    unitKeywordTable.setKeyword(v);
                                    break;
                                case Constant.AD_UNIT_KEYWORD_TABLE_INFO.COLUMN_UNIT_ID:
                                    unitKeywordTable.setUnitId(Long.valueOf(v));
                                    break;
                            }
                        }
                );
                unitKeywordTableList.add(unitKeywordTable);
                unitKeywordTableList.forEach(
                        i -> AdLevelDataHandler.handleLevel4(i, mySqlRowData.getOptionType())
                );
            }
        } else if (mySqlRowData.getTableName().equals(Constant.AD_UNIT_IT_TABLE_INFO.TABLE_NAME)) {
            List<UnitItTable> unitItTableList = new ArrayList<>();
            for (Map<String, String> map : fieldValueMap) {
                UnitItTable unitItTable = new UnitItTable();
                map.forEach(
                        (k, v) -> {
                            switch (k) {
                                case Constant.AD_UNIT_IT_TABLE_INFO.COLUMN_IT_TAG:
                                    unitItTable.setItTag(v);
                                    break;
                                case Constant.AD_UNIT_IT_TABLE_INFO.COLUMN_UNIT_ID:
                                    unitItTable.setUnitId(Long.valueOf(v));
                                    break;
                            }
                        }
                );
                unitItTableList.add(unitItTable);
                unitItTableList.forEach(
                        i -> AdLevelDataHandler.handleLevel4(i, mySqlRowData.getOptionType())
                );
            }
        } else {
            List<UnitDistrictTable> unitDistrictTableList = new ArrayList<>();
            for (Map<String, String> map : fieldValueMap) {
                UnitDistrictTable unitDistrictTable = new UnitDistrictTable();
                map.forEach(
                        (k, v) -> {
                            switch (k) {
                                case Constant.AD_UNIT_DISTRICT_TABLE_INFO.COLUMN_CITY:
                                    unitDistrictTable.setCity(v);
                                    break;
                                case Constant.AD_UNIT_DISTRICT_TABLE_INFO.COLUMN_PROVINCE:
                                    unitDistrictTable.setProvince(v);
                                    break;
                            }
                        }
                );
                unitDistrictTableList.add(unitDistrictTable);
                unitDistrictTableList.forEach(
                        i -> AdLevelDataHandler.handleLevel4(i, mySqlRowData.getOptionType())
                );
            }
        }
    }

    private void level3RowData(MySqlRowData mySqlRowData) {
        List<Map<String, String>> fieldValueMap = mySqlRowData.getFieldValueMap();
        if (mySqlRowData.getTableName().equals(Constant.AD_UNIT_TABLE_INFO.TABLE_NAME)) {
            List<AdUnitTable> adUnitTableList = new ArrayList<>();
            for (Map<String, String> map : fieldValueMap) {
                AdUnitTable adUnitTable = new AdUnitTable();
                map.forEach(
                        (k, v) -> {
                            switch (k) {
                                case Constant.AD_UNIT_TABLE_INFO.COLUMN_ID:
                                    adUnitTable.setUnitId(Long.valueOf(v));
                                    break;
                                case Constant.AD_UNIT_TABLE_INFO.COLUMN_PLAN_ID:
                                    adUnitTable.setPlanId(Long.valueOf(v));
                                    break;
                                case Constant.AD_UNIT_TABLE_INFO.COLUMN_POSITION_TYPE:
                                    adUnitTable.setPositionType(Integer.valueOf(v));
                                    break;
                                case Constant.AD_UNIT_TABLE_INFO.COLUMN_UNIT_STATUS:
                                    adUnitTable.setUnitStatus(Integer.valueOf(v));
                                    break;
                            }
                        }
                );
                adUnitTableList.add(adUnitTable);
                adUnitTableList.forEach(
                        i -> AdLevelDataHandler.handleLevel3(i, mySqlRowData.getOptionType())
                );
            }
        } else {
            List<AdCreativeUnitTable> adCreativeUnitTableList = new ArrayList<>();
            for (Map<String, String> map : fieldValueMap) {
                AdCreativeUnitTable adCreativeUnitTable = new AdCreativeUnitTable();
                map.forEach(
                        (k, v) -> {
                            switch (k) {
                                case Constant.AD_CREATIVE_UNIT_TABLE_INFO.COLUMN_CREATIVE_ID:
                                    adCreativeUnitTable.setCreativeId(Long.valueOf(v));
                                    break;
                                case Constant.AD_CREATIVE_UNIT_TABLE_INFO.COLUMN_UNIT_ID:
                                    adCreativeUnitTable.setUnitId(Long.valueOf(v));
                                    break;
                            }
                        }
                );
                adCreativeUnitTableList.add(adCreativeUnitTable);
                adCreativeUnitTableList.forEach(
                        i -> AdLevelDataHandler.handleLevel3(i, mySqlRowData.getOptionType())
                );
            }
        }
    }

    private void level2RowData(MySqlRowData mySqlRowData) {
        //如果是计划表
        List<Map<String, String>> fieldValueMap = mySqlRowData.getFieldValueMap();
        if (mySqlRowData.getTableName().equals(Constant.AD_PLAN_TABLE_INFO.TABLE_NAME)) {
            List<AdPlanTable> adPlanTableList = new ArrayList<>();
            for (Map<String, String> map : fieldValueMap) {
                AdPlanTable adPlanTable = new AdPlanTable();
                map.forEach(
                        (k, v) -> {
                            switch (k) {
                                case Constant.AD_PLAN_TABLE_INFO.COLUMN_ID:
                                    adPlanTable.setPlanId(Long.valueOf(v));
                                    break;
                                case Constant.AD_PLAN_TABLE_INFO.COLUMN_USER_ID:
                                    adPlanTable.setUserId(Long.valueOf(v));
                                    break;
                                case Constant.AD_PLAN_TABLE_INFO.COLUMN_START_DATE:
                                    adPlanTable.setStartDate(CommonUtil.parseStringDate(v));
                                    break;
                                case Constant.AD_PLAN_TABLE_INFO.COLUMN_END_DATE:
                                    adPlanTable.setEndDate(CommonUtil.parseStringDate(v));
                                    break;
                                case Constant.AD_PLAN_TABLE_INFO.COLUMN_PLAN_STATUS:
                                    adPlanTable.setPlanStatus(Integer.valueOf(v));
                                    break;
                            }
                        }
                );
                adPlanTableList.add(adPlanTable);
                for (AdPlanTable planTable : adPlanTableList) {
                    AdLevelDataHandler.handleLevel2(planTable, mySqlRowData.getOptionType());
                }
            }
        } else {
            List<AdCreativeTable> adCreativeTableList = new ArrayList<>();
            for (Map<String, String> map : fieldValueMap) {
                AdCreativeTable adCreativeTable = new AdCreativeTable();
                map.forEach(
                        (k, v) -> {
                            switch (k) {
                                case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_ID:
                                    adCreativeTable.setCreateiveId(Long.valueOf(v));
                                    break;
                                case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_AUDIT_STATUS:
                                    adCreativeTable.setAuditStatus(Integer.valueOf(v));
                                    break;
                                case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_HEIGHT:
                                    adCreativeTable.setCreateTime(CommonUtil.parseStringDate(v));
                                    break;
                                case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_MATERIAL_TYPE:
                                    adCreativeTable.setMaterialType(Integer.valueOf(v));
                                    break;
                                case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_TYPE:
                                    adCreativeTable.setType(Integer.valueOf(v));
                                    break;
                                case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_WIDTH:
                                    adCreativeTable.setWidth(Integer.valueOf(v));
                                    break;
                            }
                        }
                );
                adCreativeTableList.add(adCreativeTable);
                adCreativeTableList.forEach(
                        i -> AdLevelDataHandler.handleLevel2(i, mySqlRowData.getOptionType())
                );
            }
        }
    }
}
