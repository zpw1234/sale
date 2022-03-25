package com.netease.sale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.netease.sale.entity.Content;
import com.netease.sale.mapper.ContentMapper;
import com.netease.sale.service.ContentService;
import com.netease.sale.vo.ContentPublishVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zpw
 * @Package com.netease.sale.service.impl
 * @date 2022/3/9 14:53
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentMapper contentMapper;
    @Override
    public List<Content> findAll() {
        LambdaQueryWrapper<Content> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Content::getId,Content::getUserId,Content::getImage,Content::getTitle,
                Content::getSummary,Content::getPrice,Content::getText,Content::getNum,Content::getValid);
        return contentMapper.selectList(queryWrapper);
    }

    @Override
    public List<Content> findUnPurchasedContent() {
        return contentMapper.findUnPurchasedContent();
    }

    @Override
    public List<Content> findByUser(String id) {
        return contentMapper.findByUser(id);
    }

    @Override
    public Content findContentById(String contentId) {
        return contentMapper.selectById(contentId);
    }

    @Override
    public void updateContentNumById(String contentId, Integer num) {
        contentMapper.updateContentNumById(contentId,num);
    }

    @Override
    public Content save(Content content) {
        contentMapper.save(content);
        return content;
    }

    @Override
    public Content update(ContentPublishVo contentPublishVo) {
        Content content = contentMapper.findById(contentPublishVo.getContentId());
        BeanUtils.copyProperties(contentPublishVo,content);
        contentMapper.updateById(content);
        return content;
    }
}
