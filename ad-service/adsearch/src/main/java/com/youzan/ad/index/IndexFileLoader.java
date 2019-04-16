package com.youzan.ad.index;

import com.alibaba.fastjson.JSON;
import com.youzan.ad.dump.DConstant;
import com.youzan.ad.dump.table.*;
import com.youzan.ad.handler.AdLevelDataHandler;
import com.youzan.ad.mysql.constant.OptionType;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author TCP
 * @create 2019/4/11 19:28
 * 增加全量索引
 */
@DependsOn(value = "dataTable")
@Component
public class IndexFileLoader {
    /**
     * 从本地读取日志文件，存到list集合里面
     *
     * @param path
     * @return
     */
    public List<String> loadDumpData(String path) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(path));) {
            Stream<String> stream = bufferedReader.lines();
            List<String> list = stream.collect(Collectors.toList());
            bufferedReader.close();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 在spring启动时调用loadDumpData方法
     */
    @PostConstruct
    public void init() {
        //存放的是推广计划的log日志，拼接本地磁盘文件路径，读取到list里面
        List<String> adPlanStringList = loadDumpData(String.format("%s%", DConstant.DATA_ROOT_DIR, DConstant.AD_PLAN));
        //将json字符串反序列化成对象,调用AdLevelDataHandler.handleLevel2()方法存到内存里面
        adPlanStringList.forEach(
                adPlanString -> AdLevelDataHandler.handleLevel2(
                        JSON.parseObject(adPlanString, AdPlanTable.class),
                        OptionType.ADD)
        );
        //存放的是推广创意的log日志，拼接本地磁盘文件路径，读取到list里面
        List<String> adCreativeStringList = loadDumpData(String.format("%s%", DConstant.DATA_ROOT_DIR, DConstant.AD_CREATIVE));
        adCreativeStringList.forEach(
                adCreativeString -> AdLevelDataHandler.handleLevel2(JSON.parseObject(adCreativeString,
                        AdCreativeTable.class),
                        OptionType.ADD)
        );


        List<String> adUnitStringList = loadDumpData(String.format("%s%", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT));
        adUnitStringList.forEach(
                adUnitString -> AdLevelDataHandler.handleLevel3(
                        JSON.parseObject(adUnitString, AdUnitTable.class),
                        OptionType.ADD)
        );
        List<String> adCreativeUnitStringList = loadDumpData(String.format("%s%", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT));
        adCreativeUnitStringList.forEach(
                adCreativeUnitString -> AdLevelDataHandler.handleLevel3(
                        JSON.parseObject(adCreativeUnitString, AdCreativeUnitTable.class),
                        OptionType.ADD)
        );

        List<String> adUnitItStringList = loadDumpData(String.format("%s%", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT));
        adUnitItStringList.forEach(
                adUnitItString -> AdLevelDataHandler.handleLevel4(
                        JSON.parseObject(adUnitItString, UnitItTable.class),
                        OptionType.ADD)
        );
        List<String> adUnitKewWordStringList = loadDumpData(String.format("%s%", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT));
        adUnitItStringList.forEach(
                adUnitKewWordString -> AdLevelDataHandler.handleLevel4(
                        JSON.parseObject(adUnitKewWordString, UnitKeywordTable.class),
                        OptionType.ADD)
        );
        List<String> adUnitDistrictStringList = loadDumpData(String.format("%s%", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT));
        adUnitItStringList.forEach(
                adUnitDistrictString -> AdLevelDataHandler.handleLevel4(
                        JSON.parseObject(adUnitDistrictString, UnitDistrictTable.class),
                        OptionType.ADD)
        );
    }
}
