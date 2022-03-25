package com.netease.sale.vo;

import lombok.Data;

/**
 * @author zpw
 * @Package com.netease.sale.vo
 * @date 2022/3/8 19:21
 */
@Data
public class UserVo {
    private String id;

    private String username;

    private String password;

    private Integer isBuyer;
}
