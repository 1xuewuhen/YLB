package com.xwh.dataservice.service;

import com.xwh.api.model.ProductInfo;
import com.xwh.api.pojo.MultiProduct;
import com.xwh.api.service.ProductService;
import com.xwh.common.constants.YLBConstant;
import com.xwh.common.util.CommonUtil;
import com.xwh.dataservice.mapper.ProductInfoMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈方银
 * @date 2023/6/22
 * @since 1.0
 */

@DubboService(interfaceClass = ProductService.class, version = "1.0")
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductInfoMapper productInfoMapper;

    /**
     * @description: 按类型分页查询产品
     * @author 陈方银
     * @date 2023/6/22 10:57
     * @version 1.0
     */
    @Override
    public List<ProductInfo> queryByTypeLimit(Integer pType, Integer pageNo, Integer pageSize) {
        List<ProductInfo> productInfos = new ArrayList<>();
        if (pType == 0 || pType == 1 || pType == 2) {
            pageNo = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageNo(pageSize);
            int offset = (pageNo - 1) * pageSize;
            productInfos = productInfoMapper.selectByTypeLimit(pType, offset, pageSize);
        }


        return productInfos;
    }

    /**
     * @description: 某个产品的记录总数
     * @author 陈方银
     * @date 2023/6/22 12:22
     * @version 1.0
     */
    @Override
    public Integer queryRecordNumbers(Integer pType) {
        int count = 0;
        if (pType == 0 || pType == 1 || pType == 2) {
            count = productInfoMapper.selectCountByType(pType);
        }
        return count;
    }

    /**
     * @description: 首页多个产品数据
     * @author 陈方银
     * @date 2023/6/22 12:22
     * @version 1.0
     */
    @Override
    public MultiProduct queryIndexPageProducts() {
        List<ProductInfo> noviceTreasures = productInfoMapper.selectByTypeLimit(YLBConstant.PRODUCT_TYPE_NOVICETREASURE, 0, 1);
        List<ProductInfo> preferreds = productInfoMapper.selectByTypeLimit(YLBConstant.PRODUCT_TYPE_PREFERRED, 0, 3);
        List<ProductInfo> scatterLabels = productInfoMapper.selectByTypeLimit(YLBConstant.PRODUCT_TYPE_SCATTERLABEL, 0, 3);
        return MultiProduct.builder().noviceTreasure(noviceTreasures).preferred(preferreds).scatterLabel(scatterLabels).build();
    }
}
