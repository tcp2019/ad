package com.youzan.ad.index.adcreative;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 构建广告创意索引对象
 *
 * @Author TCP
 * @create 2019/4/2 11:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdCreativeObject {

    private Long createiveId;

    private String name;

    private Integer type;

    private Integer materialType;

    private Integer height;

    private Integer width;

    private Integer auditStatus;

    private Date createTime;

    private Date updateTime;

    /**
     * 更新广告创意索引对象
     *
     * @param newaObject
     */
    public void update(AdCreativeObject newaObject) {
        if (null != newaObject.getAuditStatus()) {
            this.auditStatus = newaObject.getAuditStatus();
        }
        if (newaObject.getCreateiveId() != null) {
            this.createiveId = newaObject.getCreateiveId();
        }
        if (newaObject.getCreateTime() != null) {
            this.createTime = newaObject.getCreateTime();
        }
        if (newaObject.getHeight() != null) {
            this.height = newaObject.getHeight();
        }
        if (newaObject.getMaterialType() != null) {
            this.materialType = newaObject.getMaterialType();
        }
        if (newaObject.getName() != null) {
            this.name = newaObject.getName();
        }
        if (newaObject.getType() != null) {
            this.type = newaObject.getType();
        }
        if (newaObject.getWidth() != null) {
            this.width = newaObject.getWidth();
        }
        if (newaObject.getUpdateTime() != null) {
            this.updateTime = newaObject.getUpdateTime();
        }
    }
}
