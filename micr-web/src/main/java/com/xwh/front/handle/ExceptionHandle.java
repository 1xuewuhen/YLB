package com.xwh.front.handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xwh.common.enums.ERRORCode;
import com.xwh.common.exception.InfoException;
import com.xwh.common.exception.UserException;
import com.xwh.front.view.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈方银
 * @date 2023/6/30
 * @since 1.0
 */

@Slf4j
@RestControllerAdvice
public class ExceptionHandle {


    @ExceptionHandler(value = {UserException.class})
    public R userException(UserException e) {
        JSONObject jsonObject = JSON.parseObject(e.getMessage());
        log.error("用户异常----{}", e.getMessage());
        return R.error().setCode(jsonObject.getInteger("code")).setMsg(jsonObject.getString("message"));
    }

    @ExceptionHandler(value = {InfoException.class})
    public R infoException(InfoException e) {
        JSONObject jsonObject = JSON.parseObject(e.getMessage());
        log.error("信息异常----{}", e.getMessage());
        return R.error().setCode(jsonObject.getInteger("code")).setMsg(jsonObject.getString("message"));
    }

    @ExceptionHandler(value = {BindException.class})
    public R validatedCheck(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> map = new HashMap<>();
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(fieldError -> {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
            return R.builder().code(ERRORCode.DATA_CHECK_ERROR.getCode()).msg(ERRORCode.DATA_CHECK_ERROR.getMessage()).data(map).build();
        }
        log.error("校验异常----{}", e.getMessage());
        return R.error().setCode(ERRORCode.SYSTEM_ERROR.getCode()).setMsg(ERRORCode.SYSTEM_ERROR.getMessage());
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public R MAXException(RuntimeException e) {
        log.error("系统异常----{}", e.getMessage());
        return R.error().setCode(ERRORCode.SYSTEM_ERROR.getCode()).setMsg(ERRORCode.SYSTEM_ERROR.getMessage());
    }
}
