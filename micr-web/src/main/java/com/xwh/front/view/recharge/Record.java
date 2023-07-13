package com.xwh.front.view.recharge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author 血无痕
 * @date 2023/7/13
 * @since 1.0
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    private Integer id;
    private String result;
    private String rechargeDate;
    private BigDecimal rechargeMoney;
}
