package com.netease.sale.controller;

import com.netease.sale.entity.Content;
import com.netease.sale.service.ContentService;
import com.netease.sale.service.UserService;
import com.netease.sale.utils.RandomUtils;
import com.netease.sale.vo.ContentPublishVo;
import com.netease.sale.vo.Result;
import com.netease.sale.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zpw
 * @Package com.netease.sale.controller
 * @date 2022/3/11 12:11
 */
@Controller
@RequestMapping("/publish")
public class PublishController {
    @Autowired
    private UserService userService;
    @Autowired
    private ContentService contentService;

    /**
     * 跳转至发布界面
     * @param session
     * @param model
     * @return
     */
    @GetMapping("")
    public String publish(HttpSession session, Model model){
        String token = (String) session.getAttribute("Authorization");
        UserVo userByToken = userService.findUserByToken(token);
        model.addAttribute("user", userByToken);
        return "/publish";
    }

    @PostMapping("/publishSubmit")
    public String publishSubmit(ContentPublishVo contentPublishVo,
                                HttpSession session, Model model){
        String token = (String) session.getAttribute("Authorization");
        UserVo userByToken = userService.findUserByToken(token);
        model.addAttribute("user", userByToken);

        String userId = userByToken.getId();
        contentPublishVo.setUserId(userId);

        Content content = new Content();
        BeanUtils.copyProperties(contentPublishVo,content);

        content.setId(RandomUtils.getRandomUUID());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        content.setCreateTime(sdf.format(date));
        content.setUpdateTime(sdf.format(date));

        Content contentInfo = contentService.save(content);
        model.addAttribute("content",contentInfo);

        return "/publicSuccess";
    }
}
