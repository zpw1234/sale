package com.netease.sale.handler;

import com.alibaba.fastjson.JSON;
import com.netease.sale.entity.User;
import com.netease.sale.service.LoginService;
import com.netease.sale.utils.UserThreadLocal;
import com.netease.sale.vo.ErrorCode;
import com.netease.sale.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zpw
 * @Package com.netease.sale.handler
 * @date 2022/3/8 20:30
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在执行controller方法之前进行执行
        /**
         * 1、需要判断请求的接口路径是否为HandlerMethod
         * 2、判断token是否为为空
         * 3、不为空，登录验证loginService
         * 4、认证成功、放行
         */
        if (!(handler instanceof HandlerMethod)){
            //handler可能是资源RequestResourceHandler springboot程序访问静态资源默认去classpath下的static目录查询
            return true;
        }
        String token = request.getHeader("Authorization");
        log.info("=================request start===========================");
        String requestURI = request.getRequestURI();
        log.info("request uri:{}",requestURI);
        log.info("request method:{}",request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");

        if (StringUtils.isBlank(token)){
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(JSON.toJSONString(result));
            return false;
        }
        User user = loginService.checkToken(token);
        if (user == null){
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(JSON.toJSONString(result));
            return false;
        }
        UserThreadLocal.put(user);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //如果不删除，ThreadLocal中用完的信息有内存泄漏的风险
        UserThreadLocal.remove();
    }
}
