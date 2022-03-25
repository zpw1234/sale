package com.netease.sale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.netease.sale.entity.User;
import com.netease.sale.mapper.UserMapper;
import com.netease.sale.service.LoginService;
import com.netease.sale.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.netease.sale.vo.ErrorCode;
import com.netease.sale.vo.Result;
import com.netease.sale.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author netease
 * @since 2022-03-07
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginService loginService;
    @Override
    public User findUser(String username, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);
        queryWrapper.eq(User::getPassword,password);

        queryWrapper.select(User::getUsername,User::getId,User::getIsBuyer);
        queryWrapper.last("limit 1");

        return userMapper.selectOne(queryWrapper);
    }

    /**
     * 根据token寻找user
     * @param token
     * @return
     */
    @Override
    public UserVo findUserByToken(String token) {
        /**
         * 1、token合法性校验 是否为空，解析是否成功，redis是否存在
         * 2、如果校验失败，返回错误
         * 3、如果成功，返回对应的结果LoginUserVo
         */
        User user = loginService.checkToken(token);
        if (user == null){
            return null;
        }
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setUsername(user.getUsername());
        userVo.setPassword(userVo.getPassword());
        userVo.setIsBuyer(user.getIsBuyer());
        return userVo;
    }

    @Override
    public User findUserById(String userId) {
        return userMapper.selectById(userId);
    }
}
