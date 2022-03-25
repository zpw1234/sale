package com.netease.sale.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.netease.sale.entity.Cart;
import com.netease.sale.vo.CartVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zpw
 * @Package com.netease.sale.mapper
 * @date 2022/3/10 10:20
 */
@Mapper
public interface CartMapper extends BaseMapper<Cart> {
    @Select("select * from cart where user_id = #{userId} and content_id = #{contentId}")
    Cart findCartByUserAndContent(String userId, String contentId);

    @Insert("insert into cart(id,user_id,content_id,num) values (#{id},#{userId},#{contentId},#{num})")
    void addCart(Cart cart);

    @Select("select " +
            "c.* ,co.title,co.price " +
            "FROM cart c " +
            "LEFT JOIN content co " +
            "on c.content_id = co.id " +
            "where c.user_id = #{userId} " +
            "order by c.create_time desc ")
    List<CartVo> findCartList(String userId);
}
