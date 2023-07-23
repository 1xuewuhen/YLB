package com.xwh.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Accessors(chain = true)
public class IncomeRecord implements Serializable {
    private Integer id;

    private Integer uid;

    private Integer prodId;

    private Integer bidId;

    private BigDecimal bidMoney;

    private Date incomeDate;

    private BigDecimal incomeMoney;

    private Integer incomeStatus;

}