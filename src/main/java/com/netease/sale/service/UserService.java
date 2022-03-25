package com.netease.sale.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.netease.sale.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.netease.sale.vo.Result;
import com.netease.sale.vo.UserVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author netease
 * @since 2022-03-07
 */
public interface UserService {

    User findUser(String username, String password);

    UserVo findUserByToken(String token);

    User findUserById(String userId);
}
