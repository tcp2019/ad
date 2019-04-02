package com.youzan.ad.index.adcreativeunit;

import com.youzan.ad.index.IndexAware;
import com.youzan.ad.index.adunit.AdUnitObject;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @Author TCP
 * @create 2019/4/2 11:43
 */
public class AdCreativeUnitIndex implements IndexAware<String, AdCreativeUnitObject> {
    /**
     * 定义全局变量map
     */
    private static Map<String, AdCreativeUnitObject> objectMap;
    /**
     * key:creativeId
     * value:单元id的set集合
     */
    private static Map<Long, Set<Long>> unitMap;
    /**
     * key:unitId
     * value:创意id的set集合
     */
    private static Map<Long, Set<Long>> creativeMap;

    static {
        objectMap = new ConcurrentHashMap<>();
        unitMap = new ConcurrentHashMap<>();
        creativeMap = new ConcurrentHashMap<>();
    }

    @Override
    public AdCreativeUnitObject get(String key) {
        return objectMap.get(key);
    }

    @Override
    public void add(String key, AdCreativeUnitObject value) {
        objectMap.put(key, value);
        Set<Long> unitSet = unitMap.get(value.getCreativeId());
        //判断单元id的set集合是否为空
        //若果为空，创建unitSet，将value.getUnitId()添加到unitSet中
        //unitMap将value.getCreativeId()和unitSet放进去
        if (CollectionUtils.isEmpty(unitSet)) {
            unitSet = new ConcurrentSkipListSet<>();
            unitSet.add(value.getUnitId());
            unitMap.put(value.getCreativeId(), unitSet);
        } else {
            //如果不为空，直接将value.getUnitId()添加到unitSet中
            //unitMap将value.getCreativeId()和unitSet放进去
            unitSet.add(value.getUnitId());
        }
        Set<Long> creativeSet = creativeMap.get(value.getUnitId());
        if (CollectionUtils.isEmpty(creativeSet)) {
            creativeSet.add(value.getCreativeId());
            creativeMap.put(value.getUnitId(), creativeSet);
        } else {
            creativeSet.add(value.getCreativeId());
        }
    }

    @Override
    public void update(String key, AdCreativeUnitObject value) {

    }

    @Override
    public void delete(String key, AdCreativeUnitObject value) {

    }
}
