package com.youzan.ad.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Author TCP
 * @create 2019/3/26 15:49
 * 创意与推广关联
 *创意
 */
@Entity
@Table(name = "creative_unit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreativeUnit {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creative_id")
    private Long creativeId;

    @Column(name = "unit_id")
    private Long  unitId;
}
