package com.netease.sale.service;

import com.netease.sale.entity.AccountDetail;

import java.util.List;

/**
 * @author zpw
 * @Package com.netease.sale.service
 * @date 2022/3/10 23:08
 */
public interface AccountDetailService {
    /**
     * 插入订单数据
     * @param accountDetail
     */
    void insert(AccountDetail accountDetail);

    List<AccountDetail> findByUserId(String userId);

    List<AccountDetail> findByContentId(String contentId);

    int deleteByUserIdAndContentId(String userId, String contentId);
}
