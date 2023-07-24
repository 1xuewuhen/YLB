package com.xwh.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xwh.common.enums.ERRORCode;
import com.xwh.common.exception.InfoException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author 陈方银
 * @date 2023/6/22
 * @since 1.0
 */
public class CommonUtil {

    public static int defaultPageNo(Integer pageNo) {
        int pNo = pageNo;
        if (pageNo < 1) {
            pNo = 1;
        }
        return pNo;
    }

    public static int defaultPageSize(Integer pageSize) {
        int pNo = pageSize;
        if (pageSize < 1) {
            pNo = 1;
        }
        return pNo;
    }

    // 手机号脱敏
    public static String phoneDesensitization(String phone) {
        String result = "***********";
        if (Objects.nonNull(phone) && phone.trim().length() == 11) {
            result = phone.substring(0, 3) + "******" + phone.substring(9, 11);
        }
        return result;
    }

    // 手机号格式
    public static boolean checkPhone(String phone) {
        if (Objects.nonNull(phone)) {
            return Pattern.matches("^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$", phone);
        }
        return false;
    }

    // 邮箱格式
    public static boolean checkEmail(String email) {
        if (StringUtils.isNotBlank(email)) {
            return Pattern.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", email);
        }
        throw new InfoException(generateJSON(ERRORCode.EMAIL_NULL_ERROR.getCode(), ERRORCode.EMAIL_NULL_ERROR.getMessage()));
    }

    // 生产json串
    public static String generateJSON(Integer code, String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("message", message);
        return JSON.toJSONString(jsonObject);
    }

    // 比较bigDecimal
    public static boolean geBigDecimal(BigDecimal n1, BigDecimal n2) {
        if (Objects.isNull(n1) && Objects.isNull(n2)) {
            throw new RuntimeException(generateJSON(ERRORCode.DATA_MONEY_ERROR.getCode(), ERRORCode.DATA_MONEY_ERROR.getMessage()));
        }
        return n1.compareTo(n2) >= 0;
    }

    public static String formatTime(Date rechargeTime) {
        String result = "";
        if (Objects.nonNull(rechargeTime)) {
            result = DateFormatUtils.format(rechargeTime, "yyyy-MM-dd");
        } else {
            result = "----";
        }
        return result;
    }
}
