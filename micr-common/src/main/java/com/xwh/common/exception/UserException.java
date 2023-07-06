package com.xwh.common.exception;

/**
 * @author 陈方银
 * @date 2023/6/30
 * @since 1.0
 */
public class UserException extends RuntimeException{
    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }
}
