package com.netease.sale.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.netease.sale.entity.AccountDetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zpw
 * @Package com.netease.sale.mapper
 * @date 2022/3/10 23:09
 */
@Mapper
public interface AccountDetailMapper extends BaseMapper<AccountDetail> {
    @Select("select * from account_detail where user_id = #{userId} order by create_time desc")
    List<AccountDetail> selectByUserId(String userId);

    @Select("select * from account_detail where content_id = #{contentId} order by create_time desc")
    List<AccountDetail> findByContentId(String contentId);

    @Delete("delete from cart where user_id = #{userId} and content_id = #{contentId}")
    int deleteByUserIdAndContentId(@Param("userId") String userId, @Param("contentId") String contentId);
}
