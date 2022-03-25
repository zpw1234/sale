package com.netease.sale.service.impl;

import com.alibaba.fastjson.JSON;
import com.netease.sale.entity.User;
import com.netease.sale.service.LoginService;
import com.netease.sale.service.UserService;
import com.netease.sale.utils.JWTUtils;
import com.netease.sale.vo.ErrorCode;
import com.netease.sale.vo.Result;
import com.netease.sale.vo.UserVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zpw
 * @Package com.netease.sale.service.impl
 * @date 2022/3/8 19:28
 */
@Service
public class LoginServiceImpl implements LoginService {
    //加密盐用于加密
    private static final String salt = "mszlu!@#";
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    /**
     * 登录校验
     * @param userVo
     * @return
     */
    @Override
    public Result checkLogin(HttpServletRequest request,UserVo userVo) {
        /**
         * 1、检查参数是否合法
         * 2、根据用户名密码去user表中查询是否存在
         * 3、如果不存在登录失败
         * 4、如果存在，使用jwt生产token返回给前端
         * 5、token放入redis中，redis token：user信息 设置过期时间（登录认证的时候先认证token字符串是否合法，去redis验证是否存在)
         */
        String username = userVo.getUsername();
        String password = userVo.getPassword();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        password = DigestUtils.md5Hex(password + salt);
        User user = userService.findUser(username,password);
        if (user == null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
//        session.setAttribute("user",user);
        String token = JWTUtils.createToken(user.getId());
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(user),1, TimeUnit.DAYS);
        HttpSession session = request.getSession();
        session.setAttribute("Authorization",token);
        return Result.success(token);
    }

    @Override
    public User checkToken(String token) {
        if (StringUtils.isBlank(token)){
            return null;
        }
        Map<String, Object> map = JWTUtils.checkToken(token);
        if (map == null){
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)){
            //过期了
            return null;
        }
        User user = JSON.parseObject(userJson, User.class);
        return user;
    }
}
