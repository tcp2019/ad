package com.youzan.ad.index.district;

import com.youzan.ad.index.IndexAware;
import com.youzan.ad.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.set.CompositeSet;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @Author TCP
 * @create 2019/4/3 13:50
 */
@Component
@Slf4j
public class UnitDistrictIndex implements IndexAware<String, Set<Long>> {
    /**
     * 定义全局局变量
     * key:省-市
     * value:Set<Long>
     */
    private static Map<String, Set<Long>> districtUnitMap;

    private static Map<Long, Set<String>> unitDistrictMap;

    @Override
    public Set<Long> get(String key) {
        return districtUnitMap.get(key);
    }

    @Override
    public void add(String key, Set<Long> value) {
        CommonUtil.getValue(key, districtUnitMap, CompositeSet::new).addAll(value);
        value.forEach(
                i -> CommonUtil.getValue(
                        i, unitDistrictMap, CompositeSet::new).add(key)
        );
    }

    @Override
    public void update(String key, Set<Long> value) {
        log.error("暂不支持更新");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        CommonUtil.getValue(key, districtUnitMap, CompositeSet::new).removeAll(value);
        value.forEach(
                i -> CommonUtil.getValue(
                        i, unitDistrictMap, ConcurrentSkipListSet::new).remove(key)
        );
    }
}
