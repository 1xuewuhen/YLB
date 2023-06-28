package com.xwh.common.util;

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
        if (Objects.nonNull(phone)){
            return Pattern.matches("^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$", phone);
        }else {
            return false;
        }
    }
}
