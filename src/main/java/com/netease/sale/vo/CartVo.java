package com.netease.sale.vo;

import lombok.Data;

/**
 * @author zpw
 * @Package com.netease.sale.vo
 * @date 2022/3/10 17:07
 */
@Data
public class CartVo {
    //购物车id
    private String id;
    //用户id
    private String userId;
    //内容id
    private String contentId;
    //内容标题
    private String title;
    //数量
    private Integer num;
    //价格
    private Integer price;
    //创建时间
    private String createTime;
    //更新时间
    private String updateTime;
}
