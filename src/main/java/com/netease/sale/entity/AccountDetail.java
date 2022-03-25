package com.netease.sale.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author zpw
 * @Package com.netease.sale.entity
 * @date 2022/3/10 10:19
 */
@Data
public class AccountDetail {
    private String id;

    private String accountId;

    private String contentId;

    private String title;

    private Integer price;

    private Integer num;

    private String icon;

    private String userId;

    private String username;

    @TableField(fill = FieldFill.INSERT)
    private String createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateTime;
}
