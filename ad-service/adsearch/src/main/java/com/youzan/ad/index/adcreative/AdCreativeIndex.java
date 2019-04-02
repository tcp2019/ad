package com.youzan.ad.index.adcreative;

import com.youzan.ad.index.IndexAware;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author TCP
 * @create 2019/4/2 11:21
 */
public class AdCreativeIndex implements IndexAware<Long, AdCreativeObject> {
    /**
     * 定义全局变量map
     */
    private static Map<Long, AdCreativeObject> objectMap;

    static {
        objectMap = new ConcurrentHashMap<>();
    }

    @Override
    public AdCreativeObject get(Long key) {
        return objectMap.get(key);
    }

    @Override
    public void add(Long key, AdCreativeObject value) {
        objectMap.put(key, value);
    }

    @Override
    public void update(Long key, AdCreativeObject value) {
        AdCreativeObject adCreativeObject = objectMap.get(key);
        if (adCreativeObject == null) {
            objectMap.put(key, value);
        } else {
            adCreativeObject.update(value);
        }
    }

    @Override
    public void delete(Long key, AdCreativeObject value) {
        objectMap.remove(key);
    }
}
