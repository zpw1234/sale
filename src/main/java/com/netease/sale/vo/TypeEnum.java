package com.netease.sale.vo;

import lombok.Getter;

/**
 * @author zpw
 * @Package com.netease.sale.vo
 * @date 2022/3/9 15:58
 */
@Getter
public enum TypeEnum {
    ALL(0, "全部"),
    NOT_BUY(1,"未购买"),
    BOUGHT(2, "已购买"),
    SOLD(3, "已卖出"),
    UNSOLD(4, "未卖出"),
    ;

    private Integer code;
    private String msg;

    TypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
