package com.youzan.ad.utils;

import java.util.Map;
import java.util.function.Supplier;

/**
 * @Author TCP
 * @create 2019/4/2 15:29
 */
public class CommonUtil {
    /**
     * 从map中根据key获取value操作可能会有下面的操作
     * Object key = map.get("key");
     * if (key == null) {
     * key = new Object();
     * map.put("key", key);
     * }
     * 1.8之后变成下面那样
     *
     * @param key
     * @param map
     * @param supplier
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> V getValue(K key, Map<K, V> map, Supplier<V> supplier) {
        return map.computeIfAbsent(key, K -> supplier.get());
    }
}
