package com.xwh.dataservice.mapper;

import com.xwh.api.model.ProductInfo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    ProductInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);

    // 利率平均值
    BigDecimal selectAvgRate();

    // 按产品类型分页查询
    List<ProductInfo> selectByTypeLimit(@Param("pType") Integer pType, @Param("offset") Integer offset, @Param("rows") Integer rows);

    //  某个产品的记录总数
    int selectCountByType(@Param("pType") Integer pType);

    void updateLeftProductMoney(@Param("id") Integer id, @Param("money") BigDecimal money);

    //更新产品是满标状态
    void updateProductStatus(@Param("id") Integer id, @Param("status") Integer status);
}