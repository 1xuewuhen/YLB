package com.xwh.api.pojo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 陈方银
 * @date 2023/6/22
 * @since 1.0
 */

@Data
@Builder
public class BaseInfo implements Serializable {
    //
    private BigDecimal historyAvgRate;
    private BigDecimal sumBigMoney;
    private Integer registerUsers;
}
