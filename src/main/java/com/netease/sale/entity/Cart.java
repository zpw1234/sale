package com.netease.sale.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author zpw
 * @Package com.netease.sale.entity
 * @date 2022/3/10 10:17
 */
@Data
public class Cart {
    private String id;

    private String userId;

    private String contentId;

    private Integer num;

    @TableField(fill = FieldFill.INSERT)
    private String createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateTime;
}
