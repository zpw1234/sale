package com.netease.sale.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zpw
 * @Package com.netease.sale.vo
 * @date 2022/3/11 15:36
 */
@Data
public class ContentPublishVo {
    private String contentId;
    private String userId;
    /** 内容标题 */
    private String title;
    /** 内容摘要 */
    private String summary;
    /** 内容正文 */
    private String text;
    /** 内容库存 */
    private Integer num;
    /** 内容价格 */
    private Integer price;
    /** 内容图标 */
    private String image;
}
