package com.xwh.front.view;

import com.xwh.common.enums.RCode;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 陈方银
 * @date 2023/6/22
 * @since 1.0
 */

@Data
@Builder
@Accessors(chain = true)
public class R {
    private Integer code;
    private String msg;
    private Object data;
    private List<?> list;
    private PageInfo page;

    public static R ok(){
        return R.builder().code(RCode.SUCCESS.getCode()).msg(RCode.SUCCESS.getMessage()).build();
    }

    public static R error(){
        return R.builder().code(RCode.UN_KNOW.getCode()).msg(RCode.UN_KNOW.getMessage()).build();
    }
}
