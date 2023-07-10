package com.xwh.api.vo;

import com.xwh.api.checkInterface.RealName;
import com.xwh.api.checkInterface.UserGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 作者:陈方银
 * 时间:2023/7/10
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RealNameVo implements Serializable {

    @NotEmpty(message = "邮箱不能为空", groups = {RealName.Authentication.class})
    @Email(message = "邮箱校验失败", regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", groups = {RealName.Authentication.class})
    private String email;

    @NotEmpty(message = "名字不能为空", groups = {RealName.Authentication.class})
    private String name;

    @NotEmpty(message = "身份证号不能为空", groups = {RealName.Authentication.class})
    @Pattern(message = "身份证号校验失败", groups = {RealName.Authentication.class}, regexp = "^\\d{8,18}|[0-9x]{8,18}|[0-9X]{8,18}?$")
    private String idCard;

    @Length(min = 4, max = 6, message = "验证码是在4-6位之间", groups = {RealName.Authentication.class})
    @NotEmpty(message = "验证码不能为空", groups = UserGroup.Register.class)
    private String code;
}
