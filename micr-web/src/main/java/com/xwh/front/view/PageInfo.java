package com.xwh.front.view;

import lombok.Builder;
import lombok.Data;

/**
 * @author 陈方银
 * @date 2023/6/22
 * @since 1.0
 * 分页数据类
 */

@Data
@Builder
public class PageInfo {
    // 页号
    private Integer pageNo;
    // 每页大小
    private Integer pageSize;
    // 总页数
    private Integer totalPage;
    // 总记录数
    private Integer totalRecord;

    public PageInfo setCalculateTotalPage() {
        if (this.totalRecord % this.pageSize == 0) {
            this.totalPage = this.totalRecord / this.pageSize;
        } else {
            this.totalPage = this.totalRecord / this.pageSize + 1;
        }
        return this;
    }
}

