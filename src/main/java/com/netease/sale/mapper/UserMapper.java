package com.netease.sale.mapper;

import com.netease.sale.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author netease
 * @since 2022-03-07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
