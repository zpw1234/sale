package com.netease.sale.controller;

import com.netease.sale.service.LoginService;
import com.netease.sale.vo.Result;
import com.netease.sale.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author zpw
 * @Package com.zpw.sale.controller
 * @date 2022/3/3 21:00
 */
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    /**
     * 登录界面
     * @return
     */
    @RequestMapping("/login")
    public String Login(){
        return "login";
    }

    /**
     * 登出
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("Authorization");
        redisTemplate.delete("TOKEN_"+token);
        session.removeAttribute("Authorization");
        return "redirect:/login";
    }

    /**
     * 校验登录
     */
    @PostMapping("/checkLogin")
    @ResponseBody
    public Result checkLogin(HttpServletRequest request,@RequestBody UserVo userVo){
        //登录验证用户，访问用户表
        return loginService.checkLogin(request,userVo);
    }
}
