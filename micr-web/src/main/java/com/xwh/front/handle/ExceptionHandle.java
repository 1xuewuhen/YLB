package com.xwh.front.handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xwh.common.enums.ERRORCode;
import com.xwh.common.exception.InfoException;
import com.xwh.front.view.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 陈方银
 * @date 2023/6/30
 * @since 1.0
 */

@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = {InfoException.class})
    public R infoException(InfoException e) {
        JSONObject jsonObject = JSON.parseObject(e.getMessage());
        return R.error().setCode(jsonObject.getInteger("code")).setMsg(jsonObject.getString("message"));
    }
}
