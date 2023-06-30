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
    SEND_INFO_ERROR(11000,"发送消息失败"),
    EMAIL_CHECK_ERROR(11001,"邮箱校验失败"),
    EMAIL_NULL_ERROR(11002,"邮箱不能为空"),
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
