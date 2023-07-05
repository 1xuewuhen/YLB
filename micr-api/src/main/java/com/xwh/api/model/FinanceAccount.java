package com.xwh.api.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class FinanceAccount implements Serializable {
    private Integer id;

    private Integer uid;

    private BigDecimal availableMoney;

}