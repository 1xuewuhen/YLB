package com.xwh.common.exception;

/**
 * @author 血无痕
 * @date 2023/7/22
 * @since 1.0
 */
public class ProductException extends RuntimeException{
    public ProductException() {
        super();
    }

    public ProductException(String message) {
        super(message);
    }
}
