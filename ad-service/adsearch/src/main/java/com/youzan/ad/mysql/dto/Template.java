package com.youzan.ad.mysql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author TCP
 * @create 2019/4/11 21:05
        */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Template {
    private String dataBase;
    private List<JsonTable> tableList;
}
