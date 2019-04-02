package com.youzan.ad.index;

/**
 * @Author TCP
 * @create 2019/4/2 10:42
 */
public interface IndexAware<K, V> {
    /**
     * 查询
     *
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 添加
     *
     * @param key
     * @param value
     */
    void add(K key, V value);

    /**
     * 修改
     *
     * @param key
     * @param value
     */
    void update(K key, V value);

    /**
     * 删除
     *
     * @param key
     * @param value
     */
    void delete(K key, V value);
}
