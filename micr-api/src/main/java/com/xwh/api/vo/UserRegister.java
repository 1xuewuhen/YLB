package com.xwh.api.vo;

import com.xwh.api.checkInterface.UserGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author 陈方银
 * @date 2023/7/1
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserRegister implements Serializable {

    @NotEmpty(message = "邮箱不能为空", groups = UserGroup.Register.class)
    @Email(message = "邮箱校验失败", regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", groups = UserGroup.Register.class)
    private String email;

    @NotEmpty(message = "密码不能为空", groups = UserGroup.Register.class)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[^\\da-zA-Z\\s]).{1,16}$", groups = UserGroup.Register.class)
    private String password;

    @NotEmpty(message = "验证码不能为空", groups = UserGroup.Register.class)
    private String code;
}
