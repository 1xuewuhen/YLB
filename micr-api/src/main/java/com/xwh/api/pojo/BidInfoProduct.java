package com.xwh.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 作者:陈方银
 * 时间:2023/6/27
 */

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BidInfoProduct implements Serializable {
    private Integer id;
    private String phone;
    private String bidTime;
    private BigDecimal bidMoney;
}
