package com.xwh.front.view.invest;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author 陈方银
 * @date 2023/6/22
 * @since 1.0
 */

@Data
@Builder
@Accessors(chain = true)
public class RankView {

    private String phone;
    private BigDecimal money;
}
