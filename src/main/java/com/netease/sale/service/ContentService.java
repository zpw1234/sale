package com.netease.sale.service;

import com.netease.sale.entity.Content;
import com.netease.sale.vo.ContentPublishVo;

import java.util.List;

/**
 * @author zpw
 * @Package com.netease.sale.service
 * @date 2022/3/9 14:53
 */
public interface ContentService {
    List<Content> findAll();

    List<Content> findUnPurchasedContent();

    /**
     * 根据用户id查询对应的商品
     * @param id
     * @return
     */
    List<Content> findByUser(String id);

    Content findContentById(String contentId);

    /**
     * 下完订单更新内容表
     * @param contentId
     * @param num
     */
    void updateContentNumById(String contentId, Integer num);

    Content save(Content content);

    Content update(ContentPublishVo contentPublishVo);
}
