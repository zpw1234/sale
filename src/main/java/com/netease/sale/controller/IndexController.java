package com.netease.sale.controller;

import com.alibaba.fastjson.JSONObject;
import com.netease.sale.entity.AccountDetail;
import com.netease.sale.entity.Content;
import com.netease.sale.entity.User;
import com.netease.sale.service.AccountDetailService;
import com.netease.sale.service.ContentService;
import com.netease.sale.service.UserService;
import com.netease.sale.vo.Result;
import com.netease.sale.vo.TypeEnum;
import com.netease.sale.vo.UserStatus;
import com.netease.sale.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Source;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zpw
 * @Package com.zpw.sale.controller
 * @date 2022/3/2 15:56
 */
@Controller
@Slf4j
public class IndexController {
    @Autowired
    private UserService userService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private AccountDetailService accountDetailService;

    @GetMapping("/")
    public String index(HttpSession session,
                        @RequestParam(value = "type", required = false, defaultValue = "0") Integer type, Model model) {
        /**
         * 登录判断用户信息
         */
        String token = (String) session.getAttribute("Authorization");
        UserVo userByToken = userService.findUserByToken(token);
        model.addAttribute("user", userByToken);
        //判断所有内容和未购买内容
        model.addAttribute("type", type);

        List<Content> contentList = null;
        //未登录状态
        if (userByToken == null) {
            contentList = contentService.findAll();
            model.addAttribute("contentList", contentList);
            return "index";
        }

        //已登录
        if (UserStatus.BUYER.getCode().equals(userByToken.getIsBuyer())){ //如果是买家
            //展示的是所有内容
            if (TypeEnum.ALL.getCode().equals(type)){
                contentList = contentService.findAll();
            }else {
                //展示未购买的内容
                contentList = contentService.findUnPurchasedContent();
            }
        }else {
            //是卖家
            contentList = contentService.findByUser(userByToken.getId());

            Map<String,Integer> sellMap = new HashMap<>();
            for (Content content : contentList) {
                List<AccountDetail> list = accountDetailService.findByContentId(content.getId());
                int size = 0;
                for (AccountDetail accountDetail : list) {
                    size += accountDetail.getNum();
                }
                sellMap.put(content.getId(),size);
            }

            model.addAttribute("sellMap",sellMap);
        }
        model.addAttribute("contentList", contentList);
        return "index";
    }

    @GetMapping("/show/{contentId}")
    public String show(@PathVariable("contentId") String contentId,Model model,HttpSession session){
        String token = (String) session.getAttribute("Authorization");
        UserVo userByToken = userService.findUserByToken(token);
        model.addAttribute("user", userByToken);
        Content content = contentService.findContentById(contentId);
        model.addAttribute("content",content);
        return "showContent";
    }
}
