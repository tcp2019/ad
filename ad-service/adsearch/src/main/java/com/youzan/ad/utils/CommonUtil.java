package com.youzan.ad.utils;

import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    /**
     * 以"-"形式拼接字符串
     *
     * @param args
     * @return
     */
    public static String stringConcat(String... args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String arg : args) {
            stringBuilder.append(arg);
            stringBuilder.append("-");
        }
        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
    }

    public static Date parseStringDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        try {
            Date parse = simpleDateFormat.parse(date);
            return DateUtils.addHours(parse, -8);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
