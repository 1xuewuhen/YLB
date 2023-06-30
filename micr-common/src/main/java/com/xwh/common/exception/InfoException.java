package com.xwh.common.exception;

/**
 * @author 陈方银
 * @date 2023/6/30
 * @since 1.0
 */
public class InfoException extends RuntimeException{
    public InfoException() {
        super();
    }

    public InfoException(String message) {
        super(message);
    }
}
