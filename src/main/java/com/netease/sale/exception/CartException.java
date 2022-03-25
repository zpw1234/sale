package com.netease.sale.exception;

import com.netease.sale.vo.ErrorCode;
import lombok.Getter;

/**
 * @author zpw
 * @Package com.netease.sale.exception
 * @date 2022/3/10 15:49
 */
@Getter
public class CartException extends RuntimeException{
    private Integer code;
    public CartException(ErrorCode errorCode){
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
    }
    public CartException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
