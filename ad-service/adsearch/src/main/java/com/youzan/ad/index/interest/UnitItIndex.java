package com.youzan.ad.index.interest;

import com.youzan.ad.index.IndexAware;
import com.youzan.ad.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @Author TCP
 * @create 2019/4/3 13:37
 */
@Slf4j
@Component
public class UnitItIndex implements IndexAware<String, Set<Long>> {
    /**
     * 创建全局变量
     */
    public static Map<String, Set<Long>> itTagUnitMap;
    public static Map<Long, Set<String>> unitItTagMap;

    static {
        itTagUnitMap = new ConcurrentHashMap<>();
        unitItTagMap = new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(String key) {
        return itTagUnitMap.get(key);
    }

    @Override
    public void add(String key, Set<Long> value) {
        CommonUtil.getValue(key, itTagUnitMap, ConcurrentSkipListSet::new).addAll(value);
        value.forEach(
                i -> CommonUtil.getValue(
                        i, unitItTagMap, ConcurrentSkipListSet::new).add(key)
        );
    }

    @Override
    public void update(String key, Set<Long> value) {
        log.error("暂不支持更新");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        CommonUtil.getValue(key, itTagUnitMap, ConcurrentSkipListSet::new).removeAll(value);
        value.forEach(
                i -> CommonUtil.getValue(
                        i, unitItTagMap, ConcurrentSkipListSet::new).remove(key)
        );
    }
}
