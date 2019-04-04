package com.youzan.ad.index.adunit;

import com.youzan.ad.index.IndexAware;
import org.jboss.jandex.IndexWriter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author TCP
 * @create 2019/4/2 11:21
 * 正排索引
 */
public class AdUnitIndex implements IndexAware<Long, AdUnitObject> {
    //定义全局变量map
    private static Map<Long, AdUnitObject> objectMap;

    static {
        objectMap = new ConcurrentHashMap<>();
    }

    @Override
    public AdUnitObject get(Long key) {
        return objectMap.get(key);
    }

    @Override
    public void add(Long key, AdUnitObject value) {
        objectMap.put(key, value);
    }

    @Override
    public void update(Long key, AdUnitObject value) {
        AdUnitObject adUnitObject = objectMap.get(key);
        if (adUnitObject == null) {
            objectMap.put(key, value);
        } else {
            adUnitObject.update(value);
        }
    }

    @Override
    public void delete(Long key, AdUnitObject value) {
        objectMap.remove(key);
    }
}
