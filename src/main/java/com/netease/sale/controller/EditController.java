package com.netease.sale.controller;

import com.netease.sale.entity.Content;
import com.netease.sale.service.ContentService;
import com.netease.sale.service.UserService;
import com.netease.sale.vo.ContentPublishVo;
import com.netease.sale.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author zpw
 * @Package com.netease.sale.controller
 * @date 2022/3/11 17:57
 */
@Controller
public class EditController {
    @Autowired
    private UserService userService;
    @Autowired
    private ContentService contentService;

    @GetMapping("/edit/{contentId}")
    public String edit(@PathVariable("contentId") String contentId, HttpSession session, Model model){
        String token = (String) session.getAttribute("Authorization");
        UserVo userByToken = userService.findUserByToken(token);
        model.addAttribute("user", userByToken);

        Content content = contentService.findContentById(contentId);
        model.addAttribute("content",content);
        return "/editContent";
    }

    @PostMapping("/editSubmit/{contentId}")
    public String editSubmit(@PathVariable("contentId")String contentId, ContentPublishVo contentPublishVo
                            ,HttpSession session,Model model){
        String token = (String) session.getAttribute("Authorization");
        UserVo userByToken = userService.findUserByToken(token);
        model.addAttribute("user", userByToken);

        contentPublishVo.setContentId(contentId);
        Content contentUpdate = contentService.update(contentPublishVo);
        model.addAttribute("content",contentUpdate);
        return "/editSuccess";
    }
}
