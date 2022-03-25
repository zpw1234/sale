package com.netease.sale.service;

import com.netease.sale.entity.Cart;
import com.netease.sale.vo.CartJSON;
import com.netease.sale.vo.CartVo;
import com.netease.sale.vo.Result;

import java.util.List;

/**
 * @author zpw
 * @Package com.netease.sale.service
 * @date 2022/3/10 10:21
 */
public interface CartService {
    /**
     * 添加购物车
     * @param cartJSON
     * @return
     */
    Result addCart(CartJSON cartJSON);

    /**
     * 根据用户id查询对应的购物车列表
     * @param userId
     * @return
     */
    List<CartVo> findCartList(String userId);
}
