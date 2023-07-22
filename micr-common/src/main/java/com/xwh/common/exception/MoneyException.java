package com.xwh.common.exception;

/**
 * @author 血无痕
 * @date 2023/7/22
 * @since 1.0
 */
public class MoneyException extends RuntimeException{
    public MoneyException() {
        super();
    }

    public MoneyException(String message) {
        super(message);
    }
}
