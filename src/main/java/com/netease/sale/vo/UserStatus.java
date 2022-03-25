package com.netease.sale.vo;

import lombok.Getter;

/**
 * @author zpw
 * @Package com.netease.sale.vo
 * @date 2022/3/9 15:47
 */
@Getter
public enum UserStatus {
    //买家
    BUYER(1,"买家"),
    //卖家
    SELLER(0,"卖家");

    private Integer code;
    private String msg;

    UserStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
