package com.xwh.api.pojo;

import com.xwh.api.model.ProductInfo;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 陈方银
 * @date 2023/6/22
 * @since 1.0
 */

@Data
@Builder
public class MultiProduct implements Serializable {

    private List<ProductInfo> noviceTreasure;
    private List<ProductInfo> preferred;
    private List<ProductInfo> scatterLabel;
}
