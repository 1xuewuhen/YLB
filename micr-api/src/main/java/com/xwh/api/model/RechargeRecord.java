package com.xwh.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RechargeRecord implements Serializable {
    private Integer id;

    private Integer uid;

    private String rechargeNo;

    private Integer rechargeStatus;

    private BigDecimal rechargeMoney;

    private Date rechargeTime;

    private String rechargeDesc;

    private String channel;
}