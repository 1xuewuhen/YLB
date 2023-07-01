package com.xwh.common.enums;

/**
 * @author 陈方银
 * @date 2023/6/30
 * @since 1.0
 */
public enum ERRORCode {

    /**
     * @description: 10000~19999 状态错误码
     * @author 陈方银
     * @date 2023/6/30 20:26
     * @version 1.0
     */
    SYSTEM_ERROR(10000,"系统错误，请稍后再试"),
    SEND_INFO_ERROR(11000,"发送消息失败"),
    EMAIL_CHECK_ERROR(11001,"邮箱校验失败"),
    EMAIL_NULL_ERROR(11002,"邮箱不能为空"),
    EMAIL_CODE_CAN_USE(11003,"请勿重复提交，验证码可以使用"),
    EMAIL_CODE_ERROR(11004,"验证码错误"),
    DATA_CHECK_ERROR(12001,"数据校验错误"),
    ;

    private Integer code;
    private String message;

    ERRORCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
