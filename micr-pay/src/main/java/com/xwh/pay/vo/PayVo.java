package com.xwh.pay.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 血无痕
 * @date 2023/7/25
 * @since 1.0
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayVo {
    private String out_trade_no;
    private String total_amount;
    private String subject;
    private String body;

}
