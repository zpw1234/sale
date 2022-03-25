package com.netease.sale.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author zpw
 * @Package com.netease.sale.entity
 * @date 2022/3/9 14:48
 */
@Data
public class Content {
    //内容id
    private String id;
    //内容发布者id
    private String userId;
    //内容标题
    private String title;
    //内容摘要
    private String summary;
    //内容图片
    private String image;
    //内容价格
    private Integer price;
    //内容正文
    private String text;
    //卖出数量
    private Integer num;
    //0表示所有，1表示未购买
    private Integer valid;

    @TableField(fill = FieldFill.INSERT)
    private String createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateTime;

}
