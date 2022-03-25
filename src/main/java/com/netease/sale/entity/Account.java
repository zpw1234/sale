package com.netease.sale.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author zpw
 * @Package com.netease.sale.entity
 * @date 2022/3/10 10:18
 */
@Data
public class Account {
    /**
     * 订单id
     */
    private String id;

    private String userId;

    private String username;

    /**
     * 订单总金额
     */
    private Integer amount;

    /**
     * 订单状态，默认0是新下单
     */
    private Integer status;

    /**
     * 订单支付状态，默认未支付
     */
    private Integer isPay;

    @TableField(fill = FieldFill.INSERT)
    private String createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateTime;
}
