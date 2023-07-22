package com.xwh.common.constants;

/**
 * @author 陈方银
 * @date 2023/6/22
 * @since 1.0
 */
public interface YLBConstant {
    // 产品类型
    Integer PRODUCT_TYPE_NOVICETREASURE = 0;
    Integer PRODUCT_TYPE_PREFERRED = 1;
    Integer PRODUCT_TYPE_SCATTERLABEL = 2;

    // 登录与注册的区别
    Integer USER_LOGIN = 1;
    Integer USER_REGISTER = 2;

    // 产品状态(0~为满标，1~满标，2~收益计划)
    Integer PRODUCT_STATUS_SELLING = 0;
    Integer PRODUCT_STATUS_SELLED = 1;
    Integer PRODUCT_STATUS_PLAN = 2;

    // 投资状态(1~成功，2~失败)
    Integer INVEST_STATUS_SUCCESS = 1;
    Integer INVEST_STATUS_FAIL = 2;

}
