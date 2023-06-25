package com.xwh.api.service;

import com.xwh.api.model.ProductInfo;
import com.xwh.api.pojo.MultiProduct;

import java.util.List;

/**
 * @author 陈方银
 * @date 2023/6/22
 * @since 1.0
 */
public interface ProductService {

    // 根据产品类型，查询产品，支持分页
    List<ProductInfo> queryByTypeLimit(Integer pType, Integer pageNo, Integer pageSize);

    // 某个产品的记录总数
    Integer queryRecordNumbers(Integer pType);

    // 首页多个产品数据
    MultiProduct queryIndexPageProducts();
}
