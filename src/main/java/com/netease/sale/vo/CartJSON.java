package com.netease.sale.vo;

import lombok.Data;

/**
 * @author zpw
 * @Package com.netease.sale.vo
 * @date 2022/3/10 10:12
 */
@Data
public class CartJSON {
    //订单id
    private String accountId;
    //用户id
    private String userId;
    //内容id
    private String contentId;
    //购买数量
    private Integer num;
}
