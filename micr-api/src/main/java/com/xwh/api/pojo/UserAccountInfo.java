package com.xwh.api.pojo;

import com.xwh.api.model.User;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 陈方银
 * @date 2023/7/13
 * @since 1.0
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccountInfo implements Serializable {
    private Integer id;
    private String phone;
    private String email;
    private String name;
    private String lastLoginTime;
    private String headerImage;
    private BigDecimal availableMoney;
}
