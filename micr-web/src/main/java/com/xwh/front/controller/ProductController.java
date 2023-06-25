package com.xwh.front.controller;

import com.xwh.api.model.ProductInfo;
import com.xwh.api.pojo.MultiProduct;
import com.xwh.common.enums.RCode;
import com.xwh.common.util.CommonUtil;
import com.xwh.front.view.PageInfo;
import com.xwh.front.view.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 陈方银
 * @date 2023/6/22
 * @since 1.0
 */
//@CrossOrigin
@RequestMapping("/v1")
@RestController
@Api(tags = "理财产品功能")
public class ProductController extends BaseController {

    /**
     * @description: 首页多个产品数据
     * @author 陈方银
     * @date 2023/6/22 11:18
     * @version 1.0
     */
    @ApiOperation(value = "首页三类产品", notes = "一个新手宝，三个优选，三个散标")
    @GetMapping("/product/index")
    public R queryProductIndex() {
        MultiProduct multiProduct = productService.queryIndexPageProducts();
        return R.ok().setData(multiProduct);
    }

    /**
     * @description: 某个产品的记录总数
     * @author 陈方银
     * @date 2023/6/22 12:29
     * @version 1.0
     */
    @ApiOperation(value = "某个产品的记录总数",notes = "分页，总数")
    @GetMapping("/product/list")
    public R queryProductByType(@RequestParam("pType") Integer pType,
                                @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "9") Integer pageSize) {
        R error = R.error();
        if (pType != null && (pType == 0 || pType == 1 || pType == 2)) {
            pageNo = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageNo(pageSize);
            // 分页处理，记录总数
            Integer recordNumbers = productService.queryRecordNumbers(pType);
            if (recordNumbers > 0) {
                // 产品集合
                List<ProductInfo> productInfos = productService.queryByTypeLimit(pType, pageNo, pageSize);

                return R.ok().setList(productInfos).setPage(PageInfo.builder()
                        .pageNo(pageNo)
                        .pageSize(pageSize)
                        .totalRecord(recordNumbers)
                        .build()
                        .setCalculateTotalPage());
            }
        } else {
            error.setCode(RCode.REQUEST_PRODUCT_TYPE_ERROR.getCode()).setMsg(RCode.REQUEST_PRODUCT_TYPE_ERROR.getMessage());
        }
        return error;
    }

}
