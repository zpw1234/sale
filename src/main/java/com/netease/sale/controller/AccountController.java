package com.netease.sale.controller;

import com.netease.sale.entity.AccountDetail;
import com.netease.sale.service.AccountDetailService;
import com.netease.sale.service.UserService;
import com.netease.sale.vo.Result;
import com.netease.sale.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * @author zpw
 * @Package com.netease.sale.controller
 * @date 2022/3/11 11:44
 */
@Controller
public class AccountController {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountDetailService accountDetailService;
    @GetMapping("/account")
    public String account(HttpSession session, Model model){
        String token = (String) session.getAttribute("Authorization");
        UserVo userByToken = userService.findUserByToken(token);
        model.addAttribute("user", userByToken);

        String userId = userByToken.getId();
        //根据userId获取account
        List<AccountDetail> accountDetailList = accountDetailService.findByUserId(userId);
        model.addAttribute("accountDetailList",accountDetailList);

        BigDecimal amount = new BigDecimal(BigInteger.ZERO);
        for (AccountDetail accountDetail : accountDetailList) {
            amount = BigDecimal.valueOf(accountDetail.getPrice())
                    .multiply(BigDecimal.valueOf(accountDetail.getNum()))
                    .add(amount);
        }
        model.addAttribute("amount",amount);
        return "/account";
    }

    @PostMapping("/deleteCartDetail")
    @ResponseBody
    public Result deleteCartDetail(@RequestParam("userId")String userId,
                                   @RequestParam("contentId")String contentId){
        int res = accountDetailService.deleteByUserIdAndContentId(userId, contentId);
        return Result.success(res);
    }
}
