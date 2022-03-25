package com.netease.sale.service.impl;

import com.netease.sale.entity.AccountDetail;
import com.netease.sale.mapper.AccountDetailMapper;
import com.netease.sale.service.AccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zpw
 * @Package com.netease.sale.service.impl
 * @date 2022/3/10 23:09
 */
@Service
public class AccountDetailServiceImpl implements AccountDetailService {
    @Autowired
    private AccountDetailMapper accountDetailMapper;
    @Override
    public void insert(AccountDetail accountDetail) {
        accountDetailMapper.insert(accountDetail);
    }

    @Override
    public List<AccountDetail> findByUserId(String userId) {
        return accountDetailMapper.selectByUserId(userId);
    }

    /**
     * 根据内容id查询对应的订单
     * @param contentId
     * @return
     */
    @Override
    public List<AccountDetail> findByContentId(String contentId) {
        return accountDetailMapper.findByContentId(contentId);
    }

    @Override
    public int deleteByUserIdAndContentId(String userId, String contentId) {
        return accountDetailMapper.deleteByUserIdAndContentId(userId,contentId);
    }
}
