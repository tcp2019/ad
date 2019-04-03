package java.com.youzan.ad.service;

import com.alibaba.fastjson.JSON;
import com.netflix.discovery.converters.Auto;
import com.youzan.ad.constant.CommonStatus;
import com.youzan.ad.dao.AdCreativeRepository;
import com.youzan.ad.dao.AdPlanRepository;
import com.youzan.ad.dao.AdUnitItRepository;
import com.youzan.ad.dao.AdUnitRepository;
import com.youzan.ad.dao.unit_condition.AdCreativeUnitRepository;
import com.youzan.ad.dump.table.AdCreativeTable;
import com.youzan.ad.dump.table.AdPlanTable;
import com.youzan.ad.dump.table.AdUnitTable;
import com.youzan.ad.entity.AdCreative;
import com.youzan.ad.entity.AdPlan;
import com.youzan.ad.entity.AdUnit;
import com.youzan.ad.vo.AdUnitResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.com.youzan.ad.Application;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author TCP
 * @create 2019/4/3 14:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Slf4j
public class DumpDateService {
    @Autowired
    private AdPlanRepository adPlanRepository;

    @Autowired
    private AdUnitRepository adUnitRepository;

    @Autowired
    private AdCreativeUnitRepository adCreativeUnitRepository;

    @Autowired
    private AdCreativeRepository adCreativeRepository;

    @Autowired
    private AdUnitItRepository adUnitItRepository;

    private void dumpAdPlanTable(String fileName) {
        //查询出所有处于有效状态的推广单元
        List<AdPlan> adPlanList = adPlanRepository.findAllByPlanStatus(
                CommonStatus.VALID.getStatus()
        );
        List<AdPlanTable> adPlanTableList = new ArrayList<>();
        //将adPlanList数据存入到adPlanTableList中
        adPlanList.forEach(
                i -> adPlanTableList.add(
                        new AdPlanTable(
                                i.getId(), i.getUserId(), i.getPlanStatus(), i.getStartDate(), i.getEndDate())
                )
        );
        //把集合以流的形式存到磁盘中
        //设置路径
        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            adPlanTableList.forEach(
                    adPlanTable -> {
                        try {
                            bufferedWriter.write(JSON.toJSONString(adPlanTable));
                            bufferedWriter.newLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    private void dumpAdUnitTable(String fileName) {
        List<AdUnit> adUnitList = adUnitRepository.findAllByUnitStatus(
                CommonStatus.VALID.getStatus()
        );
        List<AdUnitTable> adUnitTableList = new ArrayList<>();
        adUnitList.forEach(
                adUnit -> adUnitTableList.add(
                        new AdUnitTable(
                                adUnit.getId(),
                                adUnit.getUnitStatus(),
                                adUnit.getPositionType(),
                                adUnit.getPlanId())
                )
        );
        //将adUnitTableList以流的形式放到磁盘上
        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            for (AdUnitTable adUnitTable : adUnitTableList) {
                bufferedWriter.write(JSON.toJSONString(adUnitTable));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }


    private void dumpAdCreativeTable(String fileName) {
        List<AdCreative> adCreativeList = adCreativeRepository.findAll();
        List<AdCreativeTable> adCreativeTableList = new ArrayList<>();
        adCreativeList.forEach(
                adCreative -> adCreativeTableList.add(
                        new AdCreativeTable(
                                adCreative.getId(),
                                adCreative.getName(),
                                adCreative.getType(),
                                adCreative.getMaterialType(),
                                adCreative.getHeight(),
                                adCreative.getWidth(),
                                adCreative.getAuditStatus(),
                                adCreative.getCreateTime(),
                                adCreative.getUpdateTime()
                        )
                )
        );
        Path path = Paths.get(fileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            for (AdCreativeTable adCreativeTable : adCreativeTableList) {
                bufferedWriter.write(JSON.toJSONString(adCreativeTable));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
}
