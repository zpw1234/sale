package com.netease.sale.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.netease.sale.entity.Content;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zpw
 * @Package com.netease.sale.mapper
 * @date 2022/3/9 14:52
 */
@Mapper
public interface ContentMapper extends BaseMapper<Content> {

    @Select("select * from content where valid = 0")
    List<Content> findUnPurchasedContent();

    @Select("select * from content where user_id=#{id} order by create_time desc")
    List<Content> findByUser(String id);

    @Select("select * from content where id = #{contentId} order by create_time desc limit 1")
    Content findById(String contentId);

    @Update("UPDATE content SET num = num - #{num},valid = 1 where id = #{contentId}")
    void updateContentNumById(@Param("contentId") String contentId, @Param("num") Integer num);

    @Insert("INSERT INTO content(id,user_id,title,summary,image,price,text,num,create_time,update_time) " +
            "values(#{id},#{userId},#{title},#{summary},#{image},#{price},#{text},#{num},#{createTime},#{updateTime})")
    int save(Content content);
}
