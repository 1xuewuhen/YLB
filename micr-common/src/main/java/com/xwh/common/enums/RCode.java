package com.xwh.common.enums;

/**
 * @author 陈方银
 * @date 2023/6/22
 * @since 1.0
 */
public enum RCode {
    /**
     * @description:
     * 1000=2000 请求参数有误，逻辑错误
     * 2000-3000 服务器请求错误
     * 3000-4000 访问dubbo的应答结果
     * 0 默认
     * @author 陈方银
     * @date 2023/6/22 11:30
     * @version 1.0
     */
    UN_KNOW(0,"请稍后重试"),
    SUCCESS(1000,"请求成功"),
    REQUEST_PARAM_ERROR(1001,"请求参数有误"),
    REQUEST_PRODUCT_TYPE_ERROR(1002,"产品类型有误")
    ;
    private final Integer code;
    private final String message;

    RCode(Integer code, String message) {
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
