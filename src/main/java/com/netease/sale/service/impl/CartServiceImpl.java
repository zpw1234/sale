package com.netease.sale.service.impl;

import com.netease.sale.entity.Cart;
import com.netease.sale.entity.Content;
import com.netease.sale.exception.CartException;
import com.netease.sale.mapper.CartMapper;
import com.netease.sale.mapper.ContentMapper;
import com.netease.sale.service.CartService;
import com.netease.sale.utils.RandomUtils;
import com.netease.sale.vo.CartJSON;
import com.netease.sale.vo.CartVo;
import com.netease.sale.vo.ErrorCode;
import com.netease.sale.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author zpw
 * @Package com.netease.sale.service.impl
 * @date 2022/3/10 10:21
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private CartMapper cartMapper;
    @Override
    public Result addCart(CartJSON cartJSON) {
        /**
         * 1、 判断当前加入购物车的内容是否已经加入到购物车过
         * 2、当前选择的购买数量是否超过库存
         */
        //根据购物车中的内容id找到对应的内容
        Content content = contentMapper.findById(cartJSON.getContentId());
        //根据内容id和用户id找到之前的购物车数据
        Cart cart = cartMapper.findCartByUserAndContent(cartJSON.getUserId(),cartJSON.getContentId());
        if (cart == null){ //第一次放入购物车
            //如果大于库存
            if (cartJSON.getNum() > content.getNum()){
                return Result.fail(999,"库存不足");
            }
            cart = new Cart();
            cart.setId(RandomUtils.getRandomUUID());
            cart.setContentId(cartJSON.getContentId());
            cart.setUserId(cartJSON.getUserId());
            cart.setNum(cartJSON.getNum());
            cartMapper.addCart(cart);
        }else {
            //如果不是第一次加入购物车,则数目在原来的数量上增加
            int num = cart.getNum() + cartJSON.getNum();
            if (num > content.getNum()){
                return Result.fail(999,"库存不足");
            }
            cart.setNum(num);
            cartMapper.updateById(cart);
        }
        return Result.success("ok");
    }

    @Override
    public List<CartVo> findCartList(String userId) {
        return cartMapper.findCartList(userId);
    }

}
