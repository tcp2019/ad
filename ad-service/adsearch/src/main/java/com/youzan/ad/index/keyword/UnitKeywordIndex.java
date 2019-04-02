package com.youzan.ad.index.keyword;

import com.youzan.ad.index.IndexAware;
import com.youzan.ad.utils.CommonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @Author TCP
 * @create 2019/4/2 14:47
 */
public class UnitKeywordIndex implements IndexAware<String, Set<Long>> {

    private static Map<String, Set<Long>> keyWordUnitMap;

    private static Map<Long, Set<String>> unitKeyWordMap;

    static {
        keyWordUnitMap = new ConcurrentHashMap<>();
        unitKeyWordMap = new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(String key) {
        if (StringUtils.isEmpty(key)) {
            return Collections.emptySet();
        }
        Set<Long> keyWordSet = keyWordUnitMap.get(key);
        if (keyWordSet == null) {
            return Collections.emptySet();
        }
        return keyWordSet;
    }

    @Override
    public void add(String key, Set<Long> value) {
        /*
        jdk 1.8之前
        Set<Long> keyWordSet = keyWordUnitMap.get(key);
        if (CollectionUtils.isEmpty(keyWordSet)) {
            keyWordSet = new ConcurrentSkipListSet<>();
            keyWordUnitMap.put(key, keyWordSet);
        }
        keyWordSet.addAll(value);
        for (Long unitId : value) {
            Set<String> unitSet = unitKeyWordMap.get(unitId);
            if (CollectionUtils.isEmpty(unitSet)) {
                unitSet = new ConcurrentSkipListSet<>();
                unitSet.add(key);
                unitKeyWordMap.put(unitId, unitSet);
            } else {
                unitSet.add(key);
            }
        }*/
        //jdk 1.8之后
        CommonUtil.getValue(key, keyWordUnitMap, ConcurrentSkipListSet::new).addAll(value);
        for (Long unitId : value) {
            CommonUtil.getValue(unitId, unitKeyWordMap, ConcurrentSkipListSet::new).add(key);
        }
    }

    @Override
    public void update(String key, Set<Long> value) {

    }

    @Override
    public void delete(String key, Set<Long> value) {
        //jdk 1.8之后
        CommonUtil.getValue(key, keyWordUnitMap, ConcurrentSkipListSet::new).removeAll(value);
        for (Long unitId : value) {
            CommonUtil.getValue(unitId, unitKeyWordMap, ConcurrentSkipListSet::new).remove(key);
        }
    }
}
