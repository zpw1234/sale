package com.netease.sale.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.netease.sale.entity.Account;
import com.netease.sale.entity.AccountDetail;
import com.netease.sale.entity.Content;
import com.netease.sale.entity.User;
import com.netease.sale.service.AccountDetailService;
import com.netease.sale.service.CartService;
import com.netease.sale.service.ContentService;
import com.netease.sale.service.UserService;
import com.netease.sale.utils.RandomUtils;
import com.netease.sale.vo.*;
import freemarker.template.SimpleDate;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author zpw
 * @Package com.netease.sale.controller
 * @date 2022/3/9 19:18
 */
@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private AccountDetailService accountDetailService;

    @PostMapping("/addCart")
    @ResponseBody
    public Result addCart(@RequestBody CartJSON cartJSON){
        return cartService.addCart(cartJSON);
    }

    @GetMapping("/settleAccount")
    public String settleAccount(Model model, HttpSession session){
        String token = (String) session.getAttribute("Authorization");
        UserVo userByToken = userService.findUserByToken(token);
        model.addAttribute("user", userByToken);
        //根据用户id获取对应的购物车列表
        List<CartVo> cartVoList = cartService.findCartList(userByToken.getId());
        model.addAttribute("cartVoList",cartVoList);
        return "/settleAccount";
    }

    @PostMapping("/buy")
    @ResponseBody
    public Result buy(@RequestBody AccountVo accountVo){
        if (ObjectUtils.isEmpty(accountVo)){
            return Result.fail(90001,"当前购物车为空");
        }
        String userId = accountVo.getUserId();
        String accountId = RandomUtils.getRandomUUID();//下单的时候产生订单id
        List<ContentVo> contentList = JSONObject.parseArray(accountVo.getItems(),ContentVo.class);
        for (ContentVo content : contentList) {
            String contentId = content.getContentId();//内容id
            Integer num = content.getNum();//对应的数量
            Content contentById = contentService.findContentById(contentId);

            AccountDetail accountDetail = new AccountDetail();
            accountDetail.setId(RandomUtils.getRandomUUID());
            accountDetail.setAccountId(accountId);
            accountDetail.setContentId(contentId);
            accountDetail.setTitle(contentById.getTitle());
            accountDetail.setPrice(contentById.getPrice());
            accountDetail.setNum(num);
            accountDetail.setIcon(contentById.getImage());
            accountDetail.setUserId(userId);

            User user = userService.findUserById(userId);
            accountDetail.setUsername(user.getUsername());

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            accountDetail.setCreateTime(sdf.format(date));
            accountDetail.setUpdateTime(sdf.format(date));
            //插入订单表
            accountDetailService.insert(accountDetail);
            //更新内容表，将库存减少
            contentService.updateContentNumById(contentId,num);
        }
        return Result.success("购买成功");
    }


}
