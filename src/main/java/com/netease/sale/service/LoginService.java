package com.netease.sale.service;

import com.netease.sale.entity.User;
import com.netease.sale.vo.Result;
import com.netease.sale.vo.UserVo;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author zpw
 * @Package com.netease.sale.service
 * @date 2022/3/8 19:27
 */
@Transactional
public interface LoginService {
    /**
     * 登录
     * @param userVo
     * @return
     */
    Result checkLogin(HttpServletRequest request,UserVo userVo);

    User checkToken(String token);
}
