package com.youzan.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 给前台返回所有的关键词id列表
 *
 * @Author TCP
 * @create 2019/3/29 13:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitItResponse {
    List<Long> ids;
}
